package cn.iocoder.yudao.module.hotel.dal.mysql.memberlevel;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo.MemberLevelExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo.MemberLevelPageReqVO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberlevel.MemberLevelDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 会员等级 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MemberLevelMapper extends BaseMapperX<MemberLevelDO> {

    default PageResult<MemberLevelDO> selectPage(MemberLevelPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MemberLevelDO>()
                .likeIfPresent(MemberLevelDO::getName, reqVO.getName())
                .eqIfPresent(MemberLevelDO::getLevel, reqVO.getLevel())
                .betweenIfPresent(MemberLevelDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MemberLevelDO::getId));
    }

    default List<MemberLevelDO> selectList(MemberLevelExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MemberLevelDO>()
                .likeIfPresent(MemberLevelDO::getName, reqVO.getName())
                .eqIfPresent(MemberLevelDO::getLevel, reqVO.getLevel())
                .betweenIfPresent(MemberLevelDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MemberLevelDO::getId));
    }

}
