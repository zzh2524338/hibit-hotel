package cn.iocoder.yudao.module.hotel.controller.admin.roominfo;

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

import cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roominfo.RoomInfoDO;
import cn.iocoder.yudao.module.hotel.convert.roominfo.RoomInfoConvert;
import cn.iocoder.yudao.module.hotel.service.roominfo.RoomInfoService;

@Api(tags = "管理后台 - 房间信息")
@RestController
@RequestMapping("/hotel/room-info")
@Validated
public class RoomInfoController {

    @Resource
    private RoomInfoService roomInfoService;

    @PostMapping("/create")
    @ApiOperation("创建房间信息")
    @PreAuthorize("@ss.hasPermission('hotel:room-info:create')")
    public CommonResult<Long> createRoomInfo(@Valid @RequestBody RoomInfoCreateReqVO createReqVO) {
        return success(roomInfoService.createRoomInfo(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新房间信息")
    @PreAuthorize("@ss.hasPermission('hotel:room-info:update')")
    public CommonResult<Boolean> updateRoomInfo(@Valid @RequestBody RoomInfoUpdateReqVO updateReqVO) {
        roomInfoService.updateRoomInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除房间信息")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('hotel:room-info:delete')")
    public CommonResult<Boolean> deleteRoomInfo(@RequestParam("id") Long id) {
        roomInfoService.deleteRoomInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得房间信息")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('hotel:room-info:query')")
    public CommonResult<RoomInfoRespVO> getRoomInfo(@RequestParam("id") Long id) {
        RoomInfoDO roomInfo = roomInfoService.getRoomInfo(id);
        return success(RoomInfoConvert.INSTANCE.convert(roomInfo));
    }

    @GetMapping("/list")
    @ApiOperation("获得房间信息列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('hotel:room-info:query')")
    public CommonResult<List<RoomInfoRespVO>> getRoomInfoList(@RequestParam("ids") Collection<Long> ids) {
        List<RoomInfoDO> list = roomInfoService.getRoomInfoList(ids);
        return success(RoomInfoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得房间信息分页")
    @PreAuthorize("@ss.hasPermission('hotel:room-info:query')")
    public CommonResult<PageResult<RoomInfoRespVO>> getRoomInfoPage(@Valid RoomInfoPageReqVO pageVO) {
        PageResult<RoomInfoDO> pageResult = roomInfoService.getRoomInfoPage(pageVO);
        return success(RoomInfoConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出房间信息 Excel")
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
