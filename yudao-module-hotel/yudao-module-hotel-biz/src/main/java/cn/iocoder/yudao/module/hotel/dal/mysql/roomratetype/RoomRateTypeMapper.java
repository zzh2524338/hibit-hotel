package cn.iocoder.yudao.module.hotel.dal.mysql.roomratetype;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomratetype.RoomRateTypeDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.hotel.controller.admin.roomratetype.vo.*;

/**
 * 房价类型 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface RoomRateTypeMapper extends BaseMapperX<RoomRateTypeDO> {

    default PageResult<RoomRateTypeDO> selectPage(RoomRateTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoomRateTypeDO>()
                .likeIfPresent(RoomRateTypeDO::getName, reqVO.getName())
                .betweenIfPresent(RoomRateTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RoomRateTypeDO::getId));
    }

    default List<RoomRateTypeDO> selectList(RoomRateTypeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<RoomRateTypeDO>()
                .likeIfPresent(RoomRateTypeDO::getName, reqVO.getName())
                .betweenIfPresent(RoomRateTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RoomRateTypeDO::getId));
    }

}
