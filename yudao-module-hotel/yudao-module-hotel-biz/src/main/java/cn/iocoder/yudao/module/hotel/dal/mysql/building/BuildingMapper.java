package cn.iocoder.yudao.module.hotel.dal.mysql.building;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hotel.dal.dataobject.building.BuildingDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.hotel.controller.admin.building.vo.*;

/**
 * 公司分部 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface BuildingMapper extends BaseMapperX<BuildingDO> {

    default PageResult<BuildingDO> selectPage(BuildingPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BuildingDO>()
                .likeIfPresent(BuildingDO::getName, reqVO.getName())
                .eqIfPresent(BuildingDO::getManager, reqVO.getManager())
                .eqIfPresent(BuildingDO::getPhone, reqVO.getPhone())
                .eqIfPresent(BuildingDO::getFloorNum, reqVO.getFloorNum())
                .eqIfPresent(BuildingDO::getAddress, reqVO.getAddress())
                .eqIfPresent(BuildingDO::getCompanyId, reqVO.getCompanyId())
                .orderByDesc(BuildingDO::getId));
    }

    default List<BuildingDO> selectList(BuildingExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<BuildingDO>()
                .likeIfPresent(BuildingDO::getName, reqVO.getName())
                .eqIfPresent(BuildingDO::getManager, reqVO.getManager())
                .eqIfPresent(BuildingDO::getPhone, reqVO.getPhone())
                .eqIfPresent(BuildingDO::getFloorNum, reqVO.getFloorNum())
                .eqIfPresent(BuildingDO::getAddress, reqVO.getAddress())
                .eqIfPresent(BuildingDO::getCompanyId, reqVO.getCompanyId())
                .orderByDesc(BuildingDO::getId));
    }

}
