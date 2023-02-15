package cn.iocoder.yudao.module.hotel.dal.mysql.roomtyperate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtyperate.RoomTypeRateDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo.*;

/**
 * 房型价格 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface RoomTypeRateMapper extends BaseMapperX<RoomTypeRateDO> {

    default PageResult<RoomTypeRateDO> selectPage(RoomTypeRatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoomTypeRateDO>()
                .eqIfPresent(RoomTypeRateDO::getRoomTypeId, reqVO.getRoomTypeId())
                .eqIfPresent(RoomTypeRateDO::getRoomRateTypeId, reqVO.getRoomRateTypeId())
                .eqIfPresent(RoomTypeRateDO::getRoomRate, reqVO.getRoomRate())
                .betweenIfPresent(RoomTypeRateDO::getAccDate, reqVO.getAccDate())
                .betweenIfPresent(RoomTypeRateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RoomTypeRateDO::getId));
    }

    default List<RoomTypeRateDO> selectList(RoomTypeRateExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<RoomTypeRateDO>()
                .eqIfPresent(RoomTypeRateDO::getRoomTypeId, reqVO.getRoomTypeId())
                .eqIfPresent(RoomTypeRateDO::getRoomRateTypeId, reqVO.getRoomRateTypeId())
                .eqIfPresent(RoomTypeRateDO::getRoomRate, reqVO.getRoomRate())
                .betweenIfPresent(RoomTypeRateDO::getAccDate, reqVO.getAccDate())
                .betweenIfPresent(RoomTypeRateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RoomTypeRateDO::getId));
    }

    default RoomTypeRateDO selectOneByTypeIdAndAccDate(Long roomRateTypeId, Long roomTypeId, LocalDateTime start) {
        return selectOne( new LambdaQueryWrapperX<RoomTypeRateDO>()
                .eq(RoomTypeRateDO::getRoomRateTypeId, roomRateTypeId)
                .eq(RoomTypeRateDO::getRoomTypeId, roomTypeId)
                .eq(RoomTypeRateDO::getAccDate, start));
    }

}
