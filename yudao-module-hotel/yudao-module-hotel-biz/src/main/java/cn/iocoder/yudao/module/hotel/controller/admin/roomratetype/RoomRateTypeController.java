package cn.iocoder.yudao.module.hotel.controller.admin.roomratetype;

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

import cn.iocoder.yudao.module.hotel.controller.admin.roomratetype.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomratetype.RoomRateTypeDO;
import cn.iocoder.yudao.module.hotel.convert.roomratetype.RoomRateTypeConvert;
import cn.iocoder.yudao.module.hotel.service.roomratetype.RoomRateTypeService;

@Tag(name = "管理后台 - 房价类型")
@RestController
@RequestMapping("/hotel/room-rate-type")
@Validated
public class RoomRateTypeController {

    @Resource
    private RoomRateTypeService roomRateTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建房价类型")
    @PreAuthorize("@ss.hasPermission('hotel:room-rate-type:create')")
    public CommonResult<Long> createRoomRateType(@Valid @RequestBody RoomRateTypeCreateReqVO createReqVO) {
        return success(roomRateTypeService.createRoomRateType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新房价类型")
    @PreAuthorize("@ss.hasPermission('hotel:room-rate-type:update')")
    public CommonResult<Boolean> updateRoomRateType(@Valid @RequestBody RoomRateTypeUpdateReqVO updateReqVO) {
        roomRateTypeService.updateRoomRateType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除房价类型")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hotel:room-rate-type:delete')")
    public CommonResult<Boolean> deleteRoomRateType(@RequestParam("id") Long id) {
        roomRateTypeService.deleteRoomRateType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得房价类型")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hotel:room-rate-type:query')")
    public CommonResult<RoomRateTypeRespVO> getRoomRateType(@RequestParam("id") Long id) {
        RoomRateTypeDO roomRateType = roomRateTypeService.getRoomRateType(id);
        return success(RoomRateTypeConvert.INSTANCE.convert(roomRateType));
    }

    @GetMapping("/list")
    @Operation(summary = "获得房价类型列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hotel:room-rate-type:query')")
    public CommonResult<List<RoomRateTypeRespVO>> getRoomRateTypeList(@RequestParam("ids") Collection<Long> ids) {
        List<RoomRateTypeDO> list = roomRateTypeService.getRoomRateTypeList(ids);
        return success(RoomRateTypeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得房价类型分页")
    @PreAuthorize("@ss.hasPermission('hotel:room-rate-type:query')")
    public CommonResult<PageResult<RoomRateTypeRespVO>> getRoomRateTypePage(@Valid RoomRateTypePageReqVO pageVO) {
        PageResult<RoomRateTypeDO> pageResult = roomRateTypeService.getRoomRateTypePage(pageVO);
        return success(RoomRateTypeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出房价类型 Excel")
    @PreAuthorize("@ss.hasPermission('hotel:room-rate-type:export')")
    @OperateLog(type = EXPORT)
    public void exportRoomRateTypeExcel(@Valid RoomRateTypeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<RoomRateTypeDO> list = roomRateTypeService.getRoomRateTypeList(exportReqVO);
        // 导出 Excel
        List<RoomRateTypeExcelVO> datas = RoomRateTypeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "房价类型.xls", "数据", RoomRateTypeExcelVO.class, datas);
    }

}