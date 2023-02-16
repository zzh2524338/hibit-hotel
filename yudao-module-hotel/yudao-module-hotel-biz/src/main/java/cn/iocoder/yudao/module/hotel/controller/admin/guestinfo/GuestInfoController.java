package cn.iocoder.yudao.module.hotel.controller.admin.guestinfo;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo.GuestInfoCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo.GuestInfoExcelVO;
import cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo.GuestInfoExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo.GuestInfoPageReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo.GuestInfoRespVO;
import cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo.GuestInfoUpdateReqVO;
import cn.iocoder.yudao.module.hotel.convert.guestinfo.GuestInfoConvert;
import cn.iocoder.yudao.module.hotel.dal.dataobject.guestinfo.GuestInfoDO;
import cn.iocoder.yudao.module.hotel.service.guestinfo.GuestInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 客史信息")
@RestController
@RequestMapping("/hotel/guest-info")
@Validated
public class GuestInfoController {

    @Resource
    private GuestInfoService guestInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建客史信息")
    @PreAuthorize("@ss.hasPermission('hotel:guest-info:create')")
    public CommonResult<Long> createGuestInfo(@Valid @RequestBody GuestInfoCreateReqVO createReqVO) {
        return success(guestInfoService.createGuestInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客史信息")
    @PreAuthorize("@ss.hasPermission('hotel:guest-info:update')")
    public CommonResult<Boolean> updateGuestInfo(@Valid @RequestBody GuestInfoUpdateReqVO updateReqVO) {
        guestInfoService.updateGuestInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客史信息")
    @Parameter(name = "id 编号", required = true)
    @PreAuthorize("@ss.hasPermission('hotel:guest-info:delete')")
    public CommonResult<Boolean> deleteGuestInfo(@RequestParam("id") Long id) {
        guestInfoService.deleteGuestInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客史信息")
    @Parameter(name = "id 编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hotel:guest-info:query')")
    public CommonResult<GuestInfoRespVO> getGuestInfo(@RequestParam("id") Long id) {
        GuestInfoDO guestInfo = guestInfoService.getGuestInfo(id);
        return success(GuestInfoConvert.INSTANCE.convert(guestInfo));
    }

    @GetMapping("/list")
    @Operation(summary = "获得客史信息列表")
    @Parameter(name = "ids 编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hotel:guest-info:query')")
    public CommonResult<List<GuestInfoRespVO>> getGuestInfoList(@RequestParam("ids") Collection<Long> ids) {
        List<GuestInfoDO> list = guestInfoService.getGuestInfoList(ids);
        return success(GuestInfoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客史信息分页")
    @PreAuthorize("@ss.hasPermission('hotel:guest-info:query')")
    public CommonResult<PageResult<GuestInfoRespVO>> getGuestInfoPage(@Valid GuestInfoPageReqVO pageVO) {
        PageResult<GuestInfoDO> pageResult = guestInfoService.getGuestInfoPage(pageVO);
        return success(GuestInfoConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客史信息 Excel")
    @PreAuthorize("@ss.hasPermission('hotel:guest-info:export')")
    @OperateLog(type = EXPORT)
    public void exportGuestInfoExcel(@Valid GuestInfoExportReqVO exportReqVO,
            HttpServletResponse response) throws IOException {
        List<GuestInfoDO> list = guestInfoService.getGuestInfoList(exportReqVO);
        // 导出 Excel
        List<GuestInfoExcelVO> datas = GuestInfoConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "客史信息.xls", "数据", GuestInfoExcelVO.class, datas);
    }

}
