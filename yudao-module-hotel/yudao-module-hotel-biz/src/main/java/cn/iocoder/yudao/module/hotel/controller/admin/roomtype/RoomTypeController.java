package cn.iocoder.yudao.module.hotel.controller.admin.roomtype;

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

import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtype.RoomTypeDO;
import cn.iocoder.yudao.module.hotel.convert.roomtype.RoomTypeConvert;
import cn.iocoder.yudao.module.hotel.service.roomtype.RoomTypeService;

@Api(tags = "管理后台 - 房间类型")
@RestController
@RequestMapping("/hotel/room-type")
@Validated
public class RoomTypeController {

    @Resource
    private RoomTypeService roomTypeService;

    @PostMapping("/create")
    @ApiOperation("创建房间类型")
    @PreAuthorize("@ss.hasPermission('hotel:room-type:create')")
    public CommonResult<Long> createRoomType(@Valid @RequestBody RoomTypeCreateReqVO createReqVO) {
        return success(roomTypeService.createRoomType(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新房间类型")
    @PreAuthorize("@ss.hasPermission('hotel:room-type:update')")
    public CommonResult<Boolean> updateRoomType(@Valid @RequestBody RoomTypeUpdateReqVO updateReqVO) {
        roomTypeService.updateRoomType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除房间类型")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('hotel:room-type:delete')")
    public CommonResult<Boolean> deleteRoomType(@RequestParam("id") Long id) {
        roomTypeService.deleteRoomType(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得房间类型")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('hotel:room-type:query')")
    public CommonResult<RoomTypeRespVO> getRoomType(@RequestParam("id") Long id) {
        RoomTypeDO roomType = roomTypeService.getRoomType(id);
        return success(RoomTypeConvert.INSTANCE.convert(roomType));
    }

    @GetMapping("/list")
    @ApiOperation("获得房间类型列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('hotel:room-type:query')")
    public CommonResult<List<RoomTypeRespVO>> getRoomTypeList(@RequestParam("ids") Collection<Long> ids) {
        List<RoomTypeDO> list = roomTypeService.getRoomTypeList(ids);
        return success(RoomTypeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得房间类型分页")
    @PreAuthorize("@ss.hasPermission('hotel:room-type:query')")
    public CommonResult<PageResult<RoomTypeRespVO>> getRoomTypePage(@Valid RoomTypePageReqVO pageVO) {
        PageResult<RoomTypeDO> pageResult = roomTypeService.getRoomTypePage(pageVO);
        return success(RoomTypeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出房间类型 Excel")
    @PreAuthorize("@ss.hasPermission('hotel:room-type:export')")
    @OperateLog(type = EXPORT)
    public void exportRoomTypeExcel(@Valid RoomTypeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<RoomTypeDO> list = roomTypeService.getRoomTypeList(exportReqVO);
        // 导出 Excel
        List<RoomTypeExcelVO> datas = RoomTypeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "房间类型.xls", "数据", RoomTypeExcelVO.class, datas);
    }

}
