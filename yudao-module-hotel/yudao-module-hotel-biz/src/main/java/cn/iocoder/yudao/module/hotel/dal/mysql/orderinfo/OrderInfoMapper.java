package cn.iocoder.yudao.module.hotel.dal.mysql.orderinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hotel.dal.dataobject.orderinfo.OrderInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.hotel.controller.admin.orderinfo.vo.*;

/**
 * 订单 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface OrderInfoMapper extends BaseMapperX<OrderInfoDO> {

    default PageResult<OrderInfoDO> selectPage(OrderInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderInfoDO>()
                .eqIfPresent(OrderInfoDO::getStatus, reqVO.getStatus())
                .likeIfPresent(OrderInfoDO::getBookingPerson, reqVO.getBookingPerson())
                .likeIfPresent(OrderInfoDO::getContactName, reqVO.getContactName())
                .eqIfPresent(OrderInfoDO::getContactNumber, reqVO.getContactNumber())
                .eqIfPresent(OrderInfoDO::getAssure, reqVO.getAssure())
                .betweenIfPresent(OrderInfoDO::getAssureTime, reqVO.getAssureTime())
                .eqIfPresent(OrderInfoDO::getArrivalTime, reqVO.getArrivalTime())
                .eqIfPresent(OrderInfoDO::getDepartTime, reqVO.getDepartTime())
                .eqIfPresent(OrderInfoDO::getMemberInfo, reqVO.getMemberInfo())
                .orderByDesc(OrderInfoDO::getId));
    }

    default List<OrderInfoDO> selectList(OrderInfoExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<OrderInfoDO>()
                .eqIfPresent(OrderInfoDO::getStatus, reqVO.getStatus())
                .likeIfPresent(OrderInfoDO::getBookingPerson, reqVO.getBookingPerson())
                .likeIfPresent(OrderInfoDO::getContactName, reqVO.getContactName())
                .eqIfPresent(OrderInfoDO::getContactNumber, reqVO.getContactNumber())
                .eqIfPresent(OrderInfoDO::getAssure, reqVO.getAssure())
                .betweenIfPresent(OrderInfoDO::getAssureTime, reqVO.getAssureTime())
                .eqIfPresent(OrderInfoDO::getArrivalTime, reqVO.getArrivalTime())
                .eqIfPresent(OrderInfoDO::getDepartTime, reqVO.getDepartTime())
                .eqIfPresent(OrderInfoDO::getMemberInfo, reqVO.getMemberInfo())
                .orderByDesc(OrderInfoDO::getId));
    }

}
