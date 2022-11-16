package cn.iocoder.yudao.module.hotel.dal.mysql.roomtype;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtype.RoomTypeDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.*;

/**
 * 房间类型 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface RoomTypeMapper extends BaseMapperX<RoomTypeDO> {

    default PageResult<RoomTypeDO> selectPage(RoomTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoomTypeDO>()
                .leIfPresent(RoomTypeDO::getLivingPopulation, reqVO.getLivingPopulation())
                .likeIfPresent(RoomTypeDO::getName, reqVO.getName())
                .eqIfPresent(RoomTypeDO::getTotalRoom, reqVO.getTotalRoom())
                .betweenIfPresent(RoomTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RoomTypeDO::getId));
    }

    default List<RoomTypeDO> selectList(RoomTypeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<RoomTypeDO>()
                .leIfPresent(RoomTypeDO::getLivingPopulation, reqVO.getLivingPopulation())
                .likeIfPresent(RoomTypeDO::getName, reqVO.getName())
                .eqIfPresent(RoomTypeDO::getTotalRoom, reqVO.getTotalRoom())
                .betweenIfPresent(RoomTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RoomTypeDO::getId));
    }

}
