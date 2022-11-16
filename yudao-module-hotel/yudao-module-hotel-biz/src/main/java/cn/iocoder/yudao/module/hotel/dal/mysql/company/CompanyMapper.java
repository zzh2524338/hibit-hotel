package cn.iocoder.yudao.module.hotel.dal.mysql.company;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.hotel.dal.dataobject.company.CompanyDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.hotel.controller.admin.company.vo.*;

/**
 * 公司信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CompanyMapper extends BaseMapperX<CompanyDO> {

    default PageResult<CompanyDO> selectPage(CompanyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CompanyDO>()
                .likeIfPresent(CompanyDO::getName, reqVO.getName())
                .eqIfPresent(CompanyDO::getPhone, reqVO.getPhone())
                .eqIfPresent(CompanyDO::getAddress, reqVO.getAddress())
                .eqIfPresent(CompanyDO::getUnifiedSocialCreditCode, reqVO.getUnifiedSocialCreditCode())
                .betweenIfPresent(CompanyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CompanyDO::getId));
    }

    default List<CompanyDO> selectList(CompanyExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CompanyDO>()
                .likeIfPresent(CompanyDO::getName, reqVO.getName())
                .eqIfPresent(CompanyDO::getPhone, reqVO.getPhone())
                .eqIfPresent(CompanyDO::getAddress, reqVO.getAddress())
                .eqIfPresent(CompanyDO::getUnifiedSocialCreditCode, reqVO.getUnifiedSocialCreditCode())
                .betweenIfPresent(CompanyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CompanyDO::getId));
    }

}
