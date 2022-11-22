package cn.iocoder.yudao.module.hotel.controller.admin.order;

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

import cn.iocoder.yudao.module.hotel.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.order.OrderInfoDO;
import cn.iocoder.yudao.module.hotel.convert.order.OrderInfoConvert;
import cn.iocoder.yudao.module.hotel.service.order.OrderInfoService;

@Api(tags = "管理后台 - 订单")
@RestController
@RequestMapping("/hotel/order-info")
@Validated
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    @PostMapping("/create")
    @ApiOperation("创建订单")
    @PreAuthorize("@ss.hasPermission('hotel:order-info:create')")
    public CommonResult<Long> createOrderInfo(@Valid @RequestBody OrderInfoCreateReqVO createReqVO) {
        return success(orderInfoService.createOrderInfo(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新订单")
    @PreAuthorize("@ss.hasPermission('hotel:order-info:update')")
    public CommonResult<Boolean> updateOrderInfo(@Valid @RequestBody OrderInfoUpdateReqVO updateReqVO) {
        orderInfoService.updateOrderInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除订单")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('hotel:order-info:delete')")
    public CommonResult<Boolean> deleteOrderInfo(@RequestParam("id") Long id) {
        orderInfoService.deleteOrderInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得订单")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('hotel:order-info:query')")
    public CommonResult<OrderInfoRespVO> getOrderInfo(@RequestParam("id") Long id) {
        OrderInfoDO orderInfo = orderInfoService.getOrderInfo(id);
        return success(OrderInfoConvert.INSTANCE.convert(orderInfo));
    }

    @GetMapping("/list")
    @ApiOperation("获得订单列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('hotel:order-info:query')")
    public CommonResult<List<OrderInfoRespVO>> getOrderInfoList(@RequestParam("ids") Collection<Long> ids) {
        List<OrderInfoDO> list = orderInfoService.getOrderInfoList(ids);
        return success(OrderInfoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得订单分页")
    @PreAuthorize("@ss.hasPermission('hotel:order-info:query')")
    public CommonResult<PageResult<OrderInfoRespVO>> getOrderInfoPage(@Valid OrderInfoPageReqVO pageVO) {
        PageResult<OrderInfoDO> pageResult = orderInfoService.getOrderInfoPage(pageVO);
        return success(OrderInfoConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出订单 Excel")
    @PreAuthorize("@ss.hasPermission('hotel:order-info:export')")
    @OperateLog(type = EXPORT)
    public void exportOrderInfoExcel(@Valid OrderInfoExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<OrderInfoDO> list = orderInfoService.getOrderInfoList(exportReqVO);
        // 导出 Excel
        List<OrderInfoExcelVO> datas = OrderInfoConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "订单.xls", "数据", OrderInfoExcelVO.class, datas);
    }

}
