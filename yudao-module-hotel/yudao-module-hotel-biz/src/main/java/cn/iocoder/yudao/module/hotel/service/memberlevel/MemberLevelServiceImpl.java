package cn.iocoder.yudao.module.hotel.service.memberlevel;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberlevel.MemberLevelDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.hotel.convert.memberlevel.MemberLevelConvert;
import cn.iocoder.yudao.module.hotel.dal.mysql.memberlevel.MemberLevelMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.*;

/**
 * 会员等级 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MemberLevelServiceImpl implements MemberLevelService {

    @Resource
    private MemberLevelMapper memberLevelMapper;

    @Override
    public Long createMemberLevel(MemberLevelCreateReqVO createReqVO) {
        // 插入
        MemberLevelDO memberLevel = MemberLevelConvert.INSTANCE.convert(createReqVO);
        memberLevelMapper.insert(memberLevel);
        // 返回
        return memberLevel.getId();
    }

    @Override
    public void updateMemberLevel(MemberLevelUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateMemberLevelExists(updateReqVO.getId());
        // 更新
        MemberLevelDO updateObj = MemberLevelConvert.INSTANCE.convert(updateReqVO);
        memberLevelMapper.updateById(updateObj);
    }

    @Override
    public void deleteMemberLevel(Long id) {
        // 校验存在
        this.validateMemberLevelExists(id);
        // 删除
        memberLevelMapper.deleteById(id);
    }

    private void validateMemberLevelExists(Long id) {
        if (memberLevelMapper.selectById(id) == null) {
            throw exception(MEMBER_LEVEL_NOT_EXISTS);
        }
    }

    @Override
    public MemberLevelDO getMemberLevel(Long id) {
        return memberLevelMapper.selectById(id);
    }

    @Override
    public MemberLevelDO getMemberLevelByLevel(Integer level) {
        memberLevelMapper.selectOne(MemberLevelDO::getLevel, level);
    }

    @Override
    public List<MemberLevelDO> getMemberLevelList(Collection<Long> ids) {
        return memberLevelMapper.selectBatchIds(ids);
    }

    @Override
    public List<MemberLevelDO> getAllMemberLevelList() {
        return memberLevelMapper.selectList();
    }

    @Override
    public PageResult<MemberLevelDO> getMemberLevelPage(MemberLevelPageReqVO pageReqVO) {
        return memberLevelMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MemberLevelDO> getMemberLevelList(MemberLevelExportReqVO exportReqVO) {
        return memberLevelMapper.selectList(exportReqVO);
    }

}
