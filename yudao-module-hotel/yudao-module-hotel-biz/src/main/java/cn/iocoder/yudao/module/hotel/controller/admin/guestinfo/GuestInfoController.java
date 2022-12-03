package cn.iocoder.yudao.module.hotel.controller.admin.guestinfo;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.guestinfo.GuestInfoDO;
import cn.iocoder.yudao.module.hotel.convert.guestinfo.GuestInfoConvert;
import cn.iocoder.yudao.module.hotel.service.guestinfo.GuestInfoService;

@Api(tags = "管理后台 - 客史信息")
@RestController
@RequestMapping("/hotel/guest-info")
@Validated
public class GuestInfoController {

    @Resource
    private GuestInfoService guestInfoService;

    @PostMapping("/create")
    @ApiOperation("创建客史信息")
    @PreAuthorize("@ss.hasPermission('hotel:guest-info:create')")
    public CommonResult<Long> createGuestInfo(@Valid @RequestBody GuestInfoCreateReqVO createReqVO) {
        return success(guestInfoService.createGuestInfo(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新客史信息")
    @PreAuthorize("@ss.hasPermission('hotel:guest-info:update')")
    public CommonResult<Boolean> updateGuestInfo(@Valid @RequestBody GuestInfoUpdateReqVO updateReqVO) {
        guestInfoService.updateGuestInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除客史信息")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('hotel:guest-info:delete')")
    public CommonResult<Boolean> deleteGuestInfo(@RequestParam("id") Long id) {
        guestInfoService.deleteGuestInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得客史信息")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('hotel:guest-info:query')")
    public CommonResult<GuestInfoRespVO> getGuestInfo(@RequestParam("id") Long id) {
        GuestInfoDO guestInfo = guestInfoService.getGuestInfo(id);
        return success(GuestInfoConvert.INSTANCE.convert(guestInfo));
    }

    @GetMapping("/list")
    @ApiOperation("获得客史信息列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('hotel:guest-info:query')")
    public CommonResult<List<GuestInfoRespVO>> getGuestInfoList(@RequestParam("ids") Collection<Long> ids) {
        List<GuestInfoDO> list = guestInfoService.getGuestInfoList(ids);
        return success(GuestInfoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得客史信息分页")
    @PreAuthorize("@ss.hasPermission('hotel:guest-info:query')")
    public CommonResult<PageResult<GuestInfoRespVO>> getGuestInfoPage(@Valid GuestInfoPageReqVO pageVO) {
        PageResult<GuestInfoDO> pageResult = guestInfoService.getGuestInfoPage(pageVO);
        return success(GuestInfoConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出客史信息 Excel")
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
