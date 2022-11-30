package cn.iocoder.yudao.module.hotel.dal.mysql.memberinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberinfo.MemberInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.*;

/**
 * 会员信息 Mapper
 *
 * @author hibit
 */
@Mapper
public interface MemberInfoMapper extends BaseMapperX<MemberInfoDO> {

    default PageResult<MemberInfoDO> selectPage(MemberInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MemberInfoDO>()
                .likeIfPresent(MemberInfoDO::getName, reqVO.getName())
                .eqIfPresent(MemberInfoDO::getPhone, reqVO.getPhone())
                .eqIfPresent(MemberInfoDO::getLevelId, reqVO.getLevelId())
                .eqIfPresent(MemberInfoDO::getCost, reqVO.getCost())
                .orderByDesc(MemberInfoDO::getId));
    }

    default List<MemberInfoDO> selectList(MemberInfoExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MemberInfoDO>()
                .likeIfPresent(MemberInfoDO::getName, reqVO.getName())
                .eqIfPresent(MemberInfoDO::getPhone, reqVO.getPhone())
                .eqIfPresent(MemberInfoDO::getLevelId, reqVO.getLevelId())
                .eqIfPresent(MemberInfoDO::getCost, reqVO.getCost())
                .orderByDesc(MemberInfoDO::getId));
    }

    default MemberInfoDO selectInfoByPhoneNumber(String phone) {
        return selectOne(MemberInfoDO::getPhone, phone);
    }

}
