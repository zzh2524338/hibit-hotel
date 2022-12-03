package cn.iocoder.yudao.module.hotel.service.guestinfo;

import java.util.*;
import javax.validation.*;

import cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.guestinfo.GuestInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 客史信息 Service 接口
 *
 * @author 芋道源码
 */
public interface GuestInfoService extends IService<GuestInfoDO> {

    /**
     * 创建客史信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGuestInfo(@Valid GuestInfoCreateReqVO createReqVO);

    /**
     * 更新客史信息
     *
     * @param updateReqVO 更新信息
     */
    void updateGuestInfo(@Valid GuestInfoUpdateReqVO updateReqVO);

    /**
     * 删除客史信息
     *
     * @param id 编号
     */
    void deleteGuestInfo(Long id);

    /**
     * 获得客史信息
     *
     * @param id 编号
     * @return 客史信息
     */
    GuestInfoDO getGuestInfo(Long id);

    /**
     * 获得客史信息列表
     *
     * @param ids 编号
     * @return 客史信息列表
     */
    List<GuestInfoDO> getGuestInfoList(Collection<Long> ids);

    /**
     * 根据身份证号获得客史信息列表
     *
     * @param ids 编号
     * @return 客史信息列表
     */
    List<GuestInfoDO> getGuestInfoListByIdCards(Collection<String> ids);

    /**
     * 获得客史信息分页
     *
     * @param pageReqVO 分页查询
     * @return 客史信息分页
     */
    PageResult<GuestInfoDO> getGuestInfoPage(GuestInfoPageReqVO pageReqVO);

    /**
     * 获得客史信息列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 客史信息列表
     */
    List<GuestInfoDO> getGuestInfoList(GuestInfoExportReqVO exportReqVO);

    /**
     * 保存订单传来的客人信息
     * @param guestInfoCreateReqVOS 客人信息
     */
    void saveGuestInfo(List<GuestInfoCreateReqVO> guestInfoCreateReqVOS);
}
