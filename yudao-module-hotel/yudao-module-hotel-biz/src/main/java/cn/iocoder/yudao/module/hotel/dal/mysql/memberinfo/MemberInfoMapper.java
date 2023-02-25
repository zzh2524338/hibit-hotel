package cn.iocoder.yudao.module.hotel.dal.mysql.memberinfo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoPageReqVO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberinfo.MemberInfoDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.MEMBER_NAME_CANNOT_BE_BLANK;

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

    default PageResult<MemberInfoDO> selectPageByNameOrMobileOrCard(MemberInfoPageReqVO reqVO) {
        String memberName = reqVO.getName();
        if (StringUtils.isBlank(memberName)) {
            throw exception(MEMBER_NAME_CANNOT_BE_BLANK);
        }
        // MyBatis Plus 查询
        return selectPage(reqVO, new LambdaQueryWrapperX<MemberInfoDO>()
                .like(MemberInfoDO::getName, memberName)
                .or()
                .eq(MemberInfoDO::getPhone, memberName)
                .or()
                .eq(MemberInfoDO::getCardNo, memberName)
                .orderByDesc(MemberInfoDO::getId));
    }
}
