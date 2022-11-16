package cn.iocoder.yudao.module.hotel.dal.mysql.roominfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roominfo.RoomInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo.*;

/**
 * 房间信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface RoomInfoMapper extends BaseMapperX<RoomInfoDO> {

    default PageResult<RoomInfoDO> selectPage(RoomInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoomInfoDO>()
                .eqIfPresent(RoomInfoDO::getNo, reqVO.getNo())
                .eqIfPresent(RoomInfoDO::getRoomType, reqVO.getRoomType())
                .eqIfPresent(RoomInfoDO::getFloor, reqVO.getFloor())
                .eqIfPresent(RoomInfoDO::getStatus, reqVO.getStatus())
                .eqIfPresent(RoomInfoDO::getKeyInfo, reqVO.getKeyInfo())
                .betweenIfPresent(RoomInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RoomInfoDO::getId));
    }

    default List<RoomInfoDO> selectList(RoomInfoExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<RoomInfoDO>()
                .eqIfPresent(RoomInfoDO::getNo, reqVO.getNo())
                .eqIfPresent(RoomInfoDO::getRoomType, reqVO.getRoomType())
                .eqIfPresent(RoomInfoDO::getFloor, reqVO.getFloor())
                .eqIfPresent(RoomInfoDO::getStatus, reqVO.getStatusId())
                .eqIfPresent(RoomInfoDO::getKeyInfo, reqVO.getKeyInfo())
                .betweenIfPresent(RoomInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RoomInfoDO::getId));
    }

}
