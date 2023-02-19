package cn.iocoder.yudao.module.hotel.dal.mysql.guesthistory;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hotel.dal.dataobject.guesthistory.GuestHistoryDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.hotel.controller.admin.guesthistory.vo.*;

/**
 * 客史信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface GuestHistoryMapper extends BaseMapperX<GuestHistoryDO> {

    default PageResult<GuestHistoryDO> selectPage(GuestHistoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GuestHistoryDO>()
                .likeIfPresent(GuestHistoryDO::getGuestName, reqVO.getGuestName())
                .eqIfPresent(GuestHistoryDO::getPhone, reqVO.getPhone())
                .eqIfPresent(GuestHistoryDO::getCardNo, reqVO.getCardNo())
                .eqIfPresent(GuestHistoryDO::getMemberId, reqVO.getMemberId())
                .eqIfPresent(GuestHistoryDO::getLatestCheckin, reqVO.getLatestCheckin())
                .eqIfPresent(GuestHistoryDO::getCheckinNum, reqVO.getCheckinNum())
                .orderByDesc(GuestHistoryDO::getId));
    }

    default List<GuestHistoryDO> selectList(GuestHistoryExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<GuestHistoryDO>()
                .likeIfPresent(GuestHistoryDO::getGuestName, reqVO.getGuestName())
                .eqIfPresent(GuestHistoryDO::getPhone, reqVO.getPhone())
                .eqIfPresent(GuestHistoryDO::getCardNo, reqVO.getCardNo())
                .eqIfPresent(GuestHistoryDO::getMemberId, reqVO.getMemberId())
                .eqIfPresent(GuestHistoryDO::getLatestCheckin, reqVO.getLatestCheckin())
                .eqIfPresent(GuestHistoryDO::getCheckinNum, reqVO.getCheckinNum())
                .orderByDesc(GuestHistoryDO::getId));
    }

}
