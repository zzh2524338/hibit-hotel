package cn.iocoder.yudao.module.hotel.dal.mysql.roomtyperate;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo.RoomTypeRateExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo.RoomTypeRatePageReqVO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtyperate.RoomTypeRateDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

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
        LocalDateTime beginOfDay = LocalDateTimeUtil.beginOfDay(start);
        LocalDateTime endOfDay = LocalDateTimeUtil.endOfDay(start);

        return selectOne(new LambdaQueryWrapperX<RoomTypeRateDO>()
                .eq(RoomTypeRateDO::getRoomRateTypeId, roomRateTypeId)
                .eq(RoomTypeRateDO::getRoomTypeId, roomTypeId)
                .between(RoomTypeRateDO::getAccDate, beginOfDay, endOfDay));
    }

    default RoomTypeRateDO selectTodayRateByRoomTypeAndRateType(Long roomRateTypeId, Long roomTypeId) {
        return this.selectOneByTypeIdAndAccDate(roomRateTypeId, roomTypeId, LocalDateTime.now());
    }

}
