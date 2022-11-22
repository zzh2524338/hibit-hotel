package cn.iocoder.yudao.module.hotel.service.order;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.hotel.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.order.OrderInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 订单 Service 接口
 *
 * @author 芋道源码
 */
public interface OrderInfoService {

    /**
     * 创建订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrderInfo(@Valid OrderInfoCreateReqVO createReqVO);

    /**
     * 更新订单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderInfo(@Valid OrderInfoUpdateReqVO updateReqVO);

    /**
     * 删除订单
     *
     * @param id 编号
     */
    void deleteOrderInfo(Long id);

    /**
     * 获得订单
     *
     * @param id 编号
     * @return 订单
     */
    OrderInfoDO getOrderInfo(Long id);

    /**
     * 获得订单列表
     *
     * @param ids 编号
     * @return 订单列表
     */
    List<OrderInfoDO> getOrderInfoList(Collection<Long> ids);

    /**
     * 获得订单分页
     *
     * @param pageReqVO 分页查询
     * @return 订单分页
     */
    PageResult<OrderInfoDO> getOrderInfoPage(OrderInfoPageReqVO pageReqVO);

    /**
     * 获得订单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 订单列表
     */
    List<OrderInfoDO> getOrderInfoList(OrderInfoExportReqVO exportReqVO);

}
