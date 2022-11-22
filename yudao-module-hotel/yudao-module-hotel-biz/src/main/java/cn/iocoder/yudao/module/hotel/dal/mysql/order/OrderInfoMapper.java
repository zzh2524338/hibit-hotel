package cn.iocoder.yudao.module.hotel.dal.mysql.order;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hotel.dal.dataobject.order.OrderInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.hotel.controller.admin.order.vo.*;

/**
 * 订单 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface OrderInfoMapper extends BaseMapperX<OrderInfoDO> {

    default PageResult<OrderInfoDO> selectPage(OrderInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderInfoDO>()
                .eqIfPresent(OrderInfoDO::getUuid, reqVO.getUuid())
                .likeIfPresent(OrderInfoDO::getOrderGuestName, reqVO.getOrderGuestName())
                .eqIfPresent(OrderInfoDO::getContactPerson, reqVO.getContactPerson())
                .eqIfPresent(OrderInfoDO::getContactNumber, reqVO.getContactNumber())
                .eqIfPresent(OrderInfoDO::getRoomInfo, reqVO.getRoomInfo())
                .eqIfPresent(OrderInfoDO::getMemberInfo, reqVO.getMemberInfo())
                .eqIfPresent(OrderInfoDO::getGuestSourceId, reqVO.getGuestSourceId())
                .eqIfPresent(OrderInfoDO::getOrderStatus, reqVO.getOrderStatus())
                .eqIfPresent(OrderInfoDO::getCheckInTime, reqVO.getCheckInTime())
                .eqIfPresent(OrderInfoDO::getCheckOutTime, reqVO.getCheckOutTime())
                .orderByDesc(OrderInfoDO::getId));
    }

    default List<OrderInfoDO> selectList(OrderInfoExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<OrderInfoDO>()
                .eqIfPresent(OrderInfoDO::getUuid, reqVO.getUuid())
                .likeIfPresent(OrderInfoDO::getOrderGuestName, reqVO.getOrderGuestName())
                .eqIfPresent(OrderInfoDO::getContactPerson, reqVO.getContactPerson())
                .eqIfPresent(OrderInfoDO::getContactNumber, reqVO.getContactNumber())
                .eqIfPresent(OrderInfoDO::getRoomInfo, reqVO.getRoomInfo())
                .eqIfPresent(OrderInfoDO::getMemberInfo, reqVO.getMemberInfo())
                .eqIfPresent(OrderInfoDO::getGuestSourceId, reqVO.getGuestSourceId())
                .eqIfPresent(OrderInfoDO::getOrderStatus, reqVO.getOrderStatus())
                .eqIfPresent(OrderInfoDO::getCheckInTime, reqVO.getCheckInTime())
                .eqIfPresent(OrderInfoDO::getCheckOutTime, reqVO.getCheckOutTime())
                .orderByDesc(OrderInfoDO::getId));
    }

}
