package cn.iocoder.yudao.module.hotel.dal.mysql.folioinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hotel.dal.dataobject.folioinfo.FolioInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.hotel.controller.admin.folioinfo.vo.*;

/**
 * 房单信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface FolioInfoMapper extends BaseMapperX<FolioInfoDO> {

    default PageResult<FolioInfoDO> selectPage(FolioInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FolioInfoDO>()
                .eqIfPresent(FolioInfoDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(FolioInfoDO::getRoomTypeId, reqVO.getRoomTypeId())
                .eqIfPresent(FolioInfoDO::getRoomId, reqVO.getRoomId())
                .eqIfPresent(FolioInfoDO::getStatus, reqVO.getStatus())
                .eqIfPresent(FolioInfoDO::getArrivalTime, reqVO.getArrivalTime())
                .eqIfPresent(FolioInfoDO::getDepartTime, reqVO.getDepartTime())
                .orderByDesc(FolioInfoDO::getId));
    }

    default List<FolioInfoDO> selectList(FolioInfoExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<FolioInfoDO>()
                .eqIfPresent(FolioInfoDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(FolioInfoDO::getRoomTypeId, reqVO.getRoomTypeId())
                .eqIfPresent(FolioInfoDO::getRoomId, reqVO.getRoomId())
                .eqIfPresent(FolioInfoDO::getStatus, reqVO.getStatus())
                .eqIfPresent(FolioInfoDO::getArrivalTime, reqVO.getArrivalTime())
                .eqIfPresent(FolioInfoDO::getDepartTime, reqVO.getDepartTime())
                .orderByDesc(FolioInfoDO::getId));
    }

}
