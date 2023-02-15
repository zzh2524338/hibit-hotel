package cn.iocoder.yudao.module.hotel.controller.admin.roomtype;

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

import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtype.RoomTypeDO;
import cn.iocoder.yudao.module.hotel.convert.roomtype.RoomTypeConvert;
import cn.iocoder.yudao.module.hotel.service.roomtype.RoomTypeService;

@Tag(name = "管理后台 - 房型管理")
@RestController
@RequestMapping("/hotel/room-type")
@Validated
public class RoomTypeController {

    @Resource
    private RoomTypeService roomTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建房型管理")
    @PreAuthorize("@ss.hasPermission('hotel:room-type:create')")
    public CommonResult<Long> createRoomType(@Valid @RequestBody RoomTypeCreateReqVO createReqVO) {
        return success(roomTypeService.createRoomType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新房型管理")
    @PreAuthorize("@ss.hasPermission('hotel:room-type:update')")
    public CommonResult<Boolean> updateRoomType(@Valid @RequestBody RoomTypeUpdateReqVO updateReqVO) {
        roomTypeService.updateRoomType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除房型管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hotel:room-type:delete')")
    public CommonResult<Boolean> deleteRoomType(@RequestParam("id") Long id) {
        roomTypeService.deleteRoomType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得房型管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hotel:room-type:query')")
    public CommonResult<RoomTypeRespVO> getRoomType(@RequestParam("id") Long id) {
        RoomTypeDO roomType = roomTypeService.getRoomType(id);
        return success(RoomTypeConvert.INSTANCE.convert(roomType));
    }

    @GetMapping("/list")
    @Operation(summary = "获得房型管理列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hotel:room-type:query')")
    public CommonResult<List<RoomTypeRespVO>> getRoomTypeListById(@RequestParam("ids") Collection<Long> ids) {
        List<RoomTypeDO> list = roomTypeService.getRoomTypeListByIds(ids);
        return success(RoomTypeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得房型管理分页")
    @PreAuthorize("@ss.hasPermission('hotel:room-type:query')")
    public CommonResult<PageResult<RoomTypeRespVO>> getRoomTypePage(@Valid RoomTypePageReqVO pageVO) {
        PageResult<RoomTypeDO> pageResult = roomTypeService.getRoomTypePage(pageVO);
        return success(RoomTypeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出房型管理 Excel")
    @PreAuthorize("@ss.hasPermission('hotel:room-type:export')")
    @OperateLog(type = EXPORT)
    public void exportRoomTypeExcel(@Valid RoomTypeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<RoomTypeDO> list = roomTypeService.getRoomTypeList(exportReqVO);
        // 导出 Excel
        List<RoomTypeExcelVO> datas = RoomTypeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "房型管理.xls", "数据", RoomTypeExcelVO.class, datas);
    }

}
