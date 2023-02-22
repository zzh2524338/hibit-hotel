package cn.iocoder.yudao.module.hotel.controller.admin.orderinfo;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.hotel.controller.admin.order.bo.OrderInfoBaseBO;


import cn.iocoder.yudao.module.hotel.controller.admin.orderinfo.vo.OrderInfoExcelVO;
import cn.iocoder.yudao.module.hotel.controller.admin.orderinfo.vo.OrderInfoExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.orderinfo.vo.OrderInfoPageReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.orderinfo.vo.OrderInfoRespVO;
import cn.iocoder.yudao.module.hotel.controller.admin.orderinfo.vo.OrderInfoUpdateReqVO;
import cn.iocoder.yudao.module.hotel.convert.orderinfo.OrderInfoConvert;
import cn.iocoder.yudao.module.hotel.dal.dataobject.orderinfo.OrderInfoDO;

import cn.iocoder.yudao.module.hotel.service.orderinfo.OrderInfoService;
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

@Tag(name = "管理后台 - 订单")
@RestController
@RequestMapping("/hotel/order-info")
@Validated
public class OrderInfoController {

    @Resource
    private OrderInfoService orderInfoService;

    // @PostMapping("/create")
    // @Operation(summary = "创建订单")
    // @PreAuthorize("@ss.hasPermission('hotel:order-info:create')")
    // public CommonResult<Long> createOrderInfo(@Valid @RequestBody OrderInfoCreateReqVO createReqVO) {
    //     return success(orderInfoService.createOrderInfo(createReqVO));
    // }

    @PostMapping("/create")
    @Operation(summary = "创建订单")
    @PreAuthorize("@ss.hasPermission('hotel:order-info:create')")
    public CommonResult<Long> createOrderInfo(@Valid @RequestBody OrderInfoBaseBO req) {
        return success(orderInfoService.createOrderInfo(req));
    }

    @PutMapping("/update")
    @Operation(summary = "更新订单")
    @PreAuthorize("@ss.hasPermission('hotel:order-info:update')")
    public CommonResult<Boolean> updateOrderInfo(@Valid @RequestBody OrderInfoUpdateReqVO updateReqVO) {
        orderInfoService.updateOrderInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hotel:order-info:delete')")
    public CommonResult<Boolean> deleteOrderInfo(@RequestParam("id") Long id) {
        orderInfoService.deleteOrderInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hotel:order-info:query')")
    public CommonResult<OrderInfoRespVO> getOrderInfo(@RequestParam("id") Long id) {
        OrderInfoDO orderInfo = orderInfoService.getOrderInfo(id);
        return success(OrderInfoConvert.INSTANCE.convert(orderInfo));
    }

    @GetMapping("/list")
    @Operation(summary = "获得订单列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hotel:order-info:query')")
    public CommonResult<List<OrderInfoRespVO>> getOrderInfoList(@RequestParam("ids") Collection<Long> ids) {
        List<OrderInfoDO> list = orderInfoService.getOrderInfoList(ids);
        return success(OrderInfoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得订单分页")
    @PreAuthorize("@ss.hasPermission('hotel:order-info:query')")
    public CommonResult<PageResult<OrderInfoRespVO>> getOrderInfoPage(@Valid OrderInfoPageReqVO pageVO) {
        PageResult<OrderInfoDO> pageResult = orderInfoService.getOrderInfoPage(pageVO);
        return success(OrderInfoConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出订单 Excel")
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
