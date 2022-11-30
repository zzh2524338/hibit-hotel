package cn.iocoder.yudao.module.hotel.service.order;

import cn.iocoder.yudao.module.hotel.dal.dataobject.memberinfo.MemberInfoDO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtype.RoomTypeDO;
import cn.iocoder.yudao.module.hotel.dal.mysql.memberinfo.MemberInfoMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.roomtype.RoomTypeMapper;
import cn.iocoder.yudao.module.hotel.service.memberinfo.MemberInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.*;

import cn.iocoder.yudao.module.hotel.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.order.OrderInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.hotel.convert.order.OrderInfoConvert;
import cn.iocoder.yudao.module.hotel.dal.mysql.order.OrderInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.*;

/**
 * 订单 Service 实现类
 *
 * @author 芋道源码
 */
@Slf4j
@Service
@Validated
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private RoomTypeMapper roomTypeMapper;
    @Resource
    private MemberInfoService memberInfoService;

    @Override
    public Long createOrderInfo(OrderInfoCreateReqVO createReqVO) {

        // 1. 验证房型信息
        RoomTypeDO roomTypeDO = roomTypeMapper.selectById(createReqVO.getRoomTypeId());
        if (roomTypeDO == null || roomTypeDO.getDeleted()) {
            log.error("该房型已经下架，请刷新页面重新获取");
            throw exception(ROOM_TYPE_NOT_EXISTS);
        }

        // 2. 会员信息
        if (null == createReqVO.getMemberId()) {
            log.warn("订单未使用会员, roomType:{},roomNo:{},contactNum:{}", createReqVO.getRoomTypeId(),
                    createReqVO.getRoomNo(), createReqVO.getContactNumber());
        } else {
            // 查询会员信息
            MemberInfoDO memberInfo = memberInfoService.getMemberInfo(createReqVO.getMemberId());
            Integer points = memberInfo.getPoints();
            BigDecimal cost = memberInfo.getCost();
        }

        // 3. 会员信息

        createReqVO.getRoomTypeId();

        // 插入
        OrderInfoDO orderInfo = OrderInfoConvert.INSTANCE.convert(createReqVO);
        orderInfoMapper.insert(orderInfo);
        // 返回
        return orderInfo.getId();
    }

    @Override
    public void updateOrderInfo(OrderInfoUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateOrderInfoExists(updateReqVO.getId());
        // 更新
        OrderInfoDO updateObj = OrderInfoConvert.INSTANCE.convert(updateReqVO);
        orderInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderInfo(Long id) {
        // 校验存在
        this.validateOrderInfoExists(id);
        // 删除
        orderInfoMapper.deleteById(id);
    }

    private void validateOrderInfoExists(Long id) {
        if (orderInfoMapper.selectById(id) == null) {
            throw exception(ORDER_INFO_NOT_EXISTS);
        }
    }

    @Override
    public OrderInfoDO getOrderInfo(Long id) {
        return orderInfoMapper.selectById(id);
    }

    @Override
    public List<OrderInfoDO> getOrderInfoList(Collection<Long> ids) {
        return orderInfoMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<OrderInfoDO> getOrderInfoPage(OrderInfoPageReqVO pageReqVO) {
        return orderInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OrderInfoDO> getOrderInfoList(OrderInfoExportReqVO exportReqVO) {
        return orderInfoMapper.selectList(exportReqVO);
    }

}
