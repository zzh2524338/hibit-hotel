package cn.iocoder.yudao.module.hotel.service.guesthistory;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.hotel.controller.admin.guesthistory.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.guesthistory.GuestHistoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.hotel.convert.guesthistory.GuestHistoryConvert;
import cn.iocoder.yudao.module.hotel.dal.mysql.guesthistory.GuestHistoryMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.*;

/**
 * 客史信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class GuestHistoryServiceImpl implements GuestHistoryService {

    @Resource
    private GuestHistoryMapper guestHistoryMapper;

    @Override
    public Long createGuestHistory(GuestHistoryCreateReqVO createReqVO) {
        // 插入
        GuestHistoryDO guestHistory = GuestHistoryConvert.INSTANCE.convert(createReqVO);
        guestHistoryMapper.insert(guestHistory);
        // 返回
        return guestHistory.getId();
    }

    @Override
    public void updateGuestHistory(GuestHistoryUpdateReqVO updateReqVO) {
        // 校验存在
        validateGuestHistoryExists(updateReqVO.getId());
        // 更新
        GuestHistoryDO updateObj = GuestHistoryConvert.INSTANCE.convert(updateReqVO);
        guestHistoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteGuestHistory(Long id) {
        // 校验存在
        validateGuestHistoryExists(id);
        // 删除
        guestHistoryMapper.deleteById(id);
    }

    private void validateGuestHistoryExists(Long id) {
        if (guestHistoryMapper.selectById(id) == null) {
            throw exception(GUEST_HISTORY_NOT_EXISTS);
        }
    }

    @Override
    public GuestHistoryDO getGuestHistory(Long id) {
        return guestHistoryMapper.selectById(id);
    }

    @Override
    public List<GuestHistoryDO> getGuestHistoryList(Collection<Long> ids) {
        return guestHistoryMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<GuestHistoryDO> getGuestHistoryPage(GuestHistoryPageReqVO pageReqVO) {
        return guestHistoryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<GuestHistoryDO> getGuestHistoryList(GuestHistoryExportReqVO exportReqVO) {
        return guestHistoryMapper.selectList(exportReqVO);
    }

}
