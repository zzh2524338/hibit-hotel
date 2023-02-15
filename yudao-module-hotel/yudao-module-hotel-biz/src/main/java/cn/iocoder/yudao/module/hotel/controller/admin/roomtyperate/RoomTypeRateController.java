package cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate;

import cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.bo.RoomRateCreateReqBO;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtyperate.RoomTypeRateDO;
import cn.iocoder.yudao.module.hotel.convert.roomtyperate.RoomTypeRateConvert;
import cn.iocoder.yudao.module.hotel.service.roomtyperate.RoomTypeRateService;

@Tag(name = "管理后台 - 房型价格")
@RestController
@RequestMapping("/hotel/room-type-rate")
@Validated
public class RoomTypeRateController {

    @Resource
    private RoomTypeRateService roomTypeRateService;

    @PostMapping("/create")
    @Operation(summary = "创建房型价格")
    @PreAuthorize("@ss.hasPermission('hotel:room-type-rate:create')")
    public CommonResult<Long> createRoomTypeRate(@Valid @RequestBody RoomTypeRateCreateReqVO createReqVO) {
        return success(roomTypeRateService.createRoomTypeRate(createReqVO));
    }

    @PostMapping("/create/batch")
    @Operation(summary = "批量创建房型价格")
    @PreAuthorize("@ss.hasPermission('hotel:room-type-rate:create')")
    public CommonResult<Long> createRoomTypeRate(@Valid @RequestBody RoomRateCreateReqBO req) {
        return success(roomTypeRateService.createRoomTypeRate(req));
    }

    @PutMapping("/update")
    @Operation(summary = "更新房型价格")
    @PreAuthorize("@ss.hasPermission('hotel:room-type-rate:update')")
    public CommonResult<Boolean> updateRoomTypeRate(@Valid @RequestBody RoomTypeRateUpdateReqVO updateReqVO) {
        roomTypeRateService.updateRoomTypeRate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除房型价格")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hotel:room-type-rate:delete')")
    public CommonResult<Boolean> deleteRoomTypeRate(@RequestParam("id") Long id) {
        roomTypeRateService.deleteRoomTypeRate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得房型价格")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hotel:room-type-rate:query')")
    public CommonResult<RoomTypeRateRespVO> getRoomTypeRate(@RequestParam("id") Long id) {
        RoomTypeRateDO roomTypeRate = roomTypeRateService.getRoomTypeRate(id);
        return success(RoomTypeRateConvert.INSTANCE.convert(roomTypeRate));
    }

    @GetMapping("/list")
    @Operation(summary = "获得房型价格列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hotel:room-type-rate:query')")
    public CommonResult<List<RoomTypeRateRespVO>> getRoomTypeRateList(@RequestParam("ids") Collection<Long> ids) {
        List<RoomTypeRateDO> list = roomTypeRateService.getRoomTypeRateList(ids);
        return success(RoomTypeRateConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/list/date")
    @Operation(summary = "按照日期获得房型价格列表")
//    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hotel:room-type-rate:query')")
    public CommonResult<List<RoomTypeRateListRespVO>> getRoomTypeRateListByDate(@Valid RoomTypeRateExportReqVO pageVO) {
        List<RoomTypeRateListRespVO> roomTypeRatePageByDate = roomTypeRateService.getRoomTypeRatePageByDate(pageVO);
        return success(roomTypeRatePageByDate);
    }

    @GetMapping("/page")
    @Operation(summary = "获得房型价格分页")
    @PreAuthorize("@ss.hasPermission('hotel:room-type-rate:query')")
    public CommonResult<PageResult<RoomTypeRateRespVO>> getRoomTypeRatePage(@Valid RoomTypeRatePageReqVO pageVO) {
        PageResult<RoomTypeRateDO> pageResult = roomTypeRateService.getRoomTypeRatePage(pageVO);
        return success(RoomTypeRateConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出房型价格 Excel")
    @PreAuthorize("@ss.hasPermission('hotel:room-type-rate:export')")
    @OperateLog(type = EXPORT)
    public void exportRoomTypeRateExcel(@Valid RoomTypeRateExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<RoomTypeRateDO> list = roomTypeRateService.getRoomTypeRateList(exportReqVO);
        // 导出 Excel
        List<RoomTypeRateExcelVO> datas = RoomTypeRateConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "房型价格.xls", "数据", RoomTypeRateExcelVO.class, datas);
    }

}
