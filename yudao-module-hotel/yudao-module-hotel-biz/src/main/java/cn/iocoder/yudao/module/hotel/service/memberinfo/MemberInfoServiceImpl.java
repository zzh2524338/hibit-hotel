package cn.iocoder.yudao.module.hotel.service.memberinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoPageReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoUpdateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoVO;
import cn.iocoder.yudao.module.hotel.convert.memberinfo.MemberInfoConvert;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberinfo.MemberInfoDO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberlevel.MemberLevelDO;
import cn.iocoder.yudao.module.hotel.dal.mysql.memberinfo.MemberInfoMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.memberlevel.MemberLevelMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.MEMBER_INFO_NOT_EXISTS;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.MEMBER_LEVEL_NOT_EXISTS;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.MEMBER_NAME_CANNOT_BE_BLANK;

/**
 * 会员信息 Service 实现类
 *
 * @author hibit
 */
@Slf4j
@Service
@Validated
public class MemberInfoServiceImpl implements MemberInfoService {

    @Resource
    private MemberInfoMapper memberInfoMapper;
    @Resource
    private MemberLevelMapper memberLevelMapper;

    @Override
    public Long createMemberInfo(MemberInfoCreateReqVO createReqVO) {
        MemberLevelDO memberLevelDO = memberLevelMapper.selectById(createReqVO.getLevelId());
        if (memberLevelDO == null) {
            throw exception(MEMBER_LEVEL_NOT_EXISTS);
        }

        // 插入
        MemberInfoDO memberInfo = MemberInfoConvert.INSTANCE.convert(createReqVO);

        memberInfo.setExp(memberLevelDO.getStartPoints());
        memberInfoMapper.insert(memberInfo);
        // 返回
        return memberInfo.getId();
    }

    @Override
    public void updateMemberInfo(MemberInfoUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateMemberInfoExists(updateReqVO.getId());
        // 更新
        MemberInfoDO updateObj = MemberInfoConvert.INSTANCE.convert(updateReqVO);
        memberInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteMemberInfo(Long id) {
        // 校验存在
        this.validateMemberInfoExists(id);
        // 删除
        memberInfoMapper.deleteById(id);
    }

    private void validateMemberInfoExists(Long id) {
        if (memberInfoMapper.selectById(id) == null) {
            throw exception(MEMBER_INFO_NOT_EXISTS);
        }
    }

    @Override
    public MemberInfoDO getMemberInfo(Long id) {
        return memberInfoMapper.selectById(id);
    }

    @Override
    public MemberInfoDO getMemberInfoByPhone(String number) {
        return this.memberInfoMapper.selectInfoByPhoneNumber(number);
    }

    @Override
    public List<MemberInfoDO> getMemberInfoList(Collection<Long> ids) {
        return memberInfoMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MemberInfoVO> getMemberInfoPageByName(MemberInfoPageReqVO pageReqVO) {
        if (StringUtils.isBlank(pageReqVO.getName())) {
            log.error("getMemberInfoPageByName 报错，pageReqVO:{}", pageReqVO);
            throw exception(MEMBER_NAME_CANNOT_BE_BLANK);
        }
        PageResult<MemberInfoDO> memberInfoDOPageResult = memberInfoMapper.selectPageByNameOrMobileOrCard(pageReqVO);

        Map<Long, MemberLevelDO> memberLevelDOMap = memberLevelMapper.selectList()
                .stream()
                .collect(Collectors.toMap(MemberLevelDO::getId, v -> v));

        List<MemberInfoVO> memberInfoVOS = memberInfoDOPageResult.getList()
                .stream()
                .map(memberInfoDO -> {
                    MemberInfoVO memberInfoVO = MemberInfoConvert.INSTANCE.convert02(memberInfoDO);
                    MemberLevelDO memberLevelDO = memberLevelDOMap.get(memberInfoDO.getLevelId());
                    if (memberLevelDO != null) {
                        memberInfoVO.setLevelName(memberLevelDO.getName());
                    }
                    return memberInfoVO;
                })
                .toList();

        return new PageResult<>(memberInfoVOS, memberInfoDOPageResult.getTotal());
    }

    @Override
    public PageResult<MemberInfoDO> getMemberInfoPage(MemberInfoPageReqVO pageReqVO) {
        return memberInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MemberInfoDO> getMemberInfoList(MemberInfoExportReqVO exportReqVO) {
        return memberInfoMapper.selectList(exportReqVO);
    }

}
