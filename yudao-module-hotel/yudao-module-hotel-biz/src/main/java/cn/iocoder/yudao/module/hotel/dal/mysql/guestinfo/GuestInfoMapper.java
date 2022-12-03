package cn.iocoder.yudao.module.hotel.dal.mysql.guestinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hotel.dal.dataobject.guestinfo.GuestInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo.*;

/**
 * 客史信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface GuestInfoMapper extends BaseMapperX<GuestInfoDO> {

    default PageResult<GuestInfoDO> selectPage(GuestInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GuestInfoDO>()
                .likeIfPresent(GuestInfoDO::getName, reqVO.getName())
                .eqIfPresent(GuestInfoDO::getPhone, reqVO.getPhone())
                .eqIfPresent(GuestInfoDO::getStayNightNum, reqVO.getStayNightNum())
                .eqIfPresent(GuestInfoDO::getCheckinNum, reqVO.getCheckinNum())
                .betweenIfPresent(GuestInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GuestInfoDO::getId));
    }

    default List<GuestInfoDO> selectList(GuestInfoExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<GuestInfoDO>()
                .likeIfPresent(GuestInfoDO::getName, reqVO.getName())
                .eqIfPresent(GuestInfoDO::getPhone, reqVO.getPhone())
                .eqIfPresent(GuestInfoDO::getStayNightNum, reqVO.getStayNightNum())
                .eqIfPresent(GuestInfoDO::getCheckinNum, reqVO.getCheckinNum())
                .betweenIfPresent(GuestInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GuestInfoDO::getId));
    }


}
