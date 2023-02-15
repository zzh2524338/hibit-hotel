package cn.iocoder.yudao.module.hotel.controller.admin.roominfo;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo.*;
import cn.iocoder.yudao.module.hotel.convert.roominfo.RoomInfoConvert;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roominfo.RoomInfoDO;
import cn.iocoder.yudao.module.hotel.service.roominfo.RoomInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 房间信息")
@RestController
@RequestMapping("/hotel/room-info")
@Validated
public class RoomInfoController {

    @Resource
    private RoomInfoService roomInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建房间信息")
    @PreAuthorize("@ss.hasPermission('hotel:room-info:create')")
    public CommonResult<Long> createRoomInfo(@Valid @RequestBody RoomInfoCreateReqVO createReqVO) {
        return success(roomInfoService.createRoomInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新房间信息")
    @PreAuthorize("@ss.hasPermission('hotel:room-info:update')")
    public CommonResult<Boolean> updateRoomInfo(@Valid @RequestBody RoomInfoUpdateReqVO updateReqVO) {
        roomInfoService.updateRoomInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除房间信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hotel:room-info:delete')")
    public CommonResult<Boolean> deleteRoomInfo(@RequestParam("id") Long id) {
        roomInfoService.deleteRoomInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得房间信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hotel:room-info:query')")
    public CommonResult<RoomInfoRespVO> getRoomInfo(@RequestParam("id") Long id) {
        RoomInfoDO roomInfo = roomInfoService.getRoomInfo(id);
        return success(RoomInfoConvert.INSTANCE.convert(roomInfo));
    }

    @GetMapping("/list")
    @Operation(summary = "获得房间信息列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hotel:room-info:query')")
    public CommonResult<List<RoomInfoRespVO>> getRoomInfoList(@RequestParam("ids") Collection<Long> ids) {
        List<RoomInfoDO> list = roomInfoService.getRoomInfoList(ids);
        return success(RoomInfoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得房间信息分页")
    @PreAuthorize("@ss.hasPermission('hotel:room-info:query')")
    public CommonResult<PageResult<RoomInfoRespVO>> getRoomInfoPage(@Valid RoomInfoPageReqVO pageVO) {
        PageResult<RoomInfoDO> pageResult = roomInfoService.getRoomInfoPage(pageVO);
        return success(RoomInfoConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出房间信息 Excel")
    @PreAuthorize("@ss.hasPermission('hotel:room-info:export')")
    @OperateLog(type = EXPORT)
    public void exportRoomInfoExcel(@Valid RoomInfoExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<RoomInfoDO> list = roomInfoService.getRoomInfoList(exportReqVO);
        // 导出 Excel
        List<RoomInfoExcelVO> datas = RoomInfoConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "房间信息.xls", "数据", RoomInfoExcelVO.class, datas);
    }

}
