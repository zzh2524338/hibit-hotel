package cn.iocoder.yudao.module.hotel.controller.admin.roomtype;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.RoomTypeCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.RoomTypeExcelVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.RoomTypeExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.RoomTypePageReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.RoomTypeRespVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.RoomTypeUpdateReqVO;
import cn.iocoder.yudao.module.hotel.convert.roomtype.RoomTypeConvert;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtype.RoomTypeDO;
import cn.iocoder.yudao.module.hotel.service.roomtype.RoomTypeService;
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
