package cn.iocoder.yudao.module.hotel.controller.admin.guesthistory;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.hotel.controller.admin.guesthistory.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.guesthistory.GuestHistoryDO;
import cn.iocoder.yudao.module.hotel.convert.guesthistory.GuestHistoryConvert;
import cn.iocoder.yudao.module.hotel.service.guesthistory.GuestHistoryService;

@Tag(name = "管理后台 - 客史信息")
@RestController
@RequestMapping("/hotel/guest-history")
@Validated
public class GuestHistoryController {

    @Resource
    private GuestHistoryService guestHistoryService;

    @PostMapping("/create")
    @Operation(summary = "创建客史信息")
    @PreAuthorize("@ss.hasPermission('hotel:guest-history:create')")
    public CommonResult<Long> createGuestHistory(@Valid @RequestBody GuestHistoryCreateReqVO createReqVO) {
        return success(guestHistoryService.createGuestHistory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新客史信息")
    @PreAuthorize("@ss.hasPermission('hotel:guest-history:update')")
    public CommonResult<Boolean> updateGuestHistory(@Valid @RequestBody GuestHistoryUpdateReqVO updateReqVO) {
        guestHistoryService.updateGuestHistory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除客史信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hotel:guest-history:delete')")
    public CommonResult<Boolean> deleteGuestHistory(@RequestParam("id") Long id) {
        guestHistoryService.deleteGuestHistory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得客史信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hotel:guest-history:query')")
    public CommonResult<GuestHistoryRespVO> getGuestHistory(@RequestParam("id") Long id) {
        GuestHistoryDO guestHistory = guestHistoryService.getGuestHistory(id);
        return success(GuestHistoryConvert.INSTANCE.convert(guestHistory));
    }

    @GetMapping("/list")
    @Operation(summary = "获得客史信息列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hotel:guest-history:query')")
    public CommonResult<List<GuestHistoryRespVO>> getGuestHistoryList(@RequestParam("ids") Collection<Long> ids) {
        List<GuestHistoryDO> list = guestHistoryService.getGuestHistoryList(ids);
        return success(GuestHistoryConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得客史信息分页")
    @PreAuthorize("@ss.hasPermission('hotel:guest-history:query')")
    public CommonResult<PageResult<GuestHistoryRespVO>> getGuestHistoryPage(@Valid GuestHistoryPageReqVO pageVO) {
        PageResult<GuestHistoryDO> pageResult = guestHistoryService.getGuestHistoryPage(pageVO);
        return success(GuestHistoryConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出客史信息 Excel")
    @PreAuthorize("@ss.hasPermission('hotel:guest-history:export')")
    @OperateLog(type = EXPORT)
    public void exportGuestHistoryExcel(@Valid GuestHistoryExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<GuestHistoryDO> list = guestHistoryService.getGuestHistoryList(exportReqVO);
        // 导出 Excel
        List<GuestHistoryExcelVO> datas = GuestHistoryConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "客史信息.xls", "数据", GuestHistoryExcelVO.class, datas);
    }

}
