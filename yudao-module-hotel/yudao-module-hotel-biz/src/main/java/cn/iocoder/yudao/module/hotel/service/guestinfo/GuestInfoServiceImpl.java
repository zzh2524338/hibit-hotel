package cn.iocoder.yudao.module.hotel.service.guestinfo;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.IdcardUtil.Idcard;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.guestinfo.GuestInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.hotel.convert.guestinfo.GuestInfoConvert;
import cn.iocoder.yudao.module.hotel.dal.mysql.guestinfo.GuestInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.*;

/**
 * 客史信息 Service 实现类
 *
 * @author 芋道源码
 */
@Slf4j
@Service
@Validated
public class GuestInfoServiceImpl implements GuestInfoService {

    @Resource
    private GuestInfoMapper guestInfoMapper;

    @Override
    public Long createGuestInfo(GuestInfoCreateReqVO guestInfoCreateReqVO) {
        if (!IdcardUtil.isValidCard(guestInfoCreateReqVO.getIdCard())) {
            log.error("身份证校验失败，guestInfoCreateReqVO:{}", guestInfoCreateReqVO);
            throw exception(ID_CARD_INVALID, guestInfoCreateReqVO.getName(), guestInfoCreateReqVO.getIdCard());
        }

        // 插入
        GuestInfoDO guestInfo = GuestInfoConvert.INSTANCE.convert(guestInfoCreateReqVO);

        Idcard idcardInfo = IdcardUtil.getIdcardInfo(guestInfoCreateReqVO.getIdCard());
        guestInfo.setBirthday(idcardInfo.getBirthDate().toLocalDateTime().toLocalDate());
        guestInfo.setGender(idcardInfo.getGender());

        if (StringUtils.isBlank(guestInfoCreateReqVO.getAddress())) {
            guestInfo.setAddress(idcardInfo.getProvince() + idcardInfo.getCityCode());
        }

        if (StringUtils.isBlank(guestInfoCreateReqVO.getAuthOrganization())) {
            guestInfo.setAuthOrganization(idcardInfo.getProvince());
        }

        guestInfo.setLatestCheckin(LocalDateTime.now());

        guestInfoMapper.insert(guestInfo);

        // 返回
        return guestInfo.getId();
    }

    @Override
    public void updateGuestInfo(GuestInfoUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateGuestInfoExists(updateReqVO.getId());
        // 更新
        GuestInfoDO updateObj = GuestInfoConvert.INSTANCE.convert(updateReqVO);
        guestInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteGuestInfo(Long id) {
        // 校验存在
        this.validateGuestInfoExists(id);
        // 删除
        guestInfoMapper.deleteById(id);
    }

    private void validateGuestInfoExists(Long id) {
        if (guestInfoMapper.selectById(id) == null) {
            throw exception(GUEST_INFO_NOT_EXISTS);
        }
    }

    @Override
    public GuestInfoDO getGuestInfo(Long id) {
        return guestInfoMapper.selectById(id);
    }

    @Override
    public List<GuestInfoDO> getGuestInfoList(Collection<Long> ids) {
        return guestInfoMapper.selectBatchIds(ids);
    }


    @Override
    public List<GuestInfoDO> getGuestInfoListByIdCards(Collection<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }

        return guestInfoMapper.selectList(GuestInfoDO::getIdCard, ids);
    }

    @Override
    public PageResult<GuestInfoDO> getGuestInfoPage(GuestInfoPageReqVO pageReqVO) {
        return guestInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<GuestInfoDO> getGuestInfoList(GuestInfoExportReqVO exportReqVO) {
        return guestInfoMapper.selectList(exportReqVO);
    }

    @Override
    public void saveGuestInfo(List<GuestInfoCreateReqVO> guestInfoCreateReqVOS) {
        Map<String, GuestInfoCreateReqVO> idGuestMap = guestInfoCreateReqVOS
                .stream()
                .collect(Collectors.toMap(GuestInfoCreateReqVO::getIdCard, v -> v));

        List<GuestInfoDO> guestInfoDOS = guestInfoMapper.selectList(GuestInfoDO::getIdCard, idGuestMap.keySet());

        List<GuestInfoDO> guestInfoListByIdCards = this.getGuestInfoListByIdCards(idGuestMap.keySet());
    }

}
