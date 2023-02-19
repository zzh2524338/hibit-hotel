package cn.iocoder.yudao.module.hotel.service.guesthistory;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.hotel.controller.admin.guesthistory.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.guesthistory.GuestHistoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 客史信息 Service 接口
 *
 * @author 芋道源码
 */
public interface GuestHistoryService {

    /**
     * 创建客史信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGuestHistory(@Valid GuestHistoryCreateReqVO createReqVO);

    /**
     * 更新客史信息
     *
     * @param updateReqVO 更新信息
     */
    void updateGuestHistory(@Valid GuestHistoryUpdateReqVO updateReqVO);

    /**
     * 删除客史信息
     *
     * @param id 编号
     */
    void deleteGuestHistory(Long id);

    /**
     * 获得客史信息
     *
     * @param id 编号
     * @return 客史信息
     */
    GuestHistoryDO getGuestHistory(Long id);

    /**
     * 获得客史信息列表
     *
     * @param ids 编号
     * @return 客史信息列表
     */
    List<GuestHistoryDO> getGuestHistoryList(Collection<Long> ids);

    /**
     * 获得客史信息分页
     *
     * @param pageReqVO 分页查询
     * @return 客史信息分页
     */
    PageResult<GuestHistoryDO> getGuestHistoryPage(GuestHistoryPageReqVO pageReqVO);

    /**
     * 获得客史信息列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 客史信息列表
     */
    List<GuestHistoryDO> getGuestHistoryList(GuestHistoryExportReqVO exportReqVO);

}
