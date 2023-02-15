package cn.iocoder.yudao.module.hotel.service.roomtyperate;

import java.util.*;
import javax.validation.*;

import cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.bo.RoomRateCreateReqBO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtyperate.RoomTypeRateDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 房型价格 Service 接口
 *
 * @author 芋道源码
 */
public interface RoomTypeRateService {

    /**
     * 创建房型价格
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRoomTypeRate(@Valid RoomTypeRateCreateReqVO createReqVO);

    /**
     * 创建房型价格
     *
     * @param req 创建信息
     * @return 编号
     */
    Long createRoomTypeRate(@Valid RoomRateCreateReqBO req);

    /**
     * 更新房型价格
     *
     * @param updateReqVO 更新信息
     */
    void updateRoomTypeRate(@Valid RoomTypeRateUpdateReqVO updateReqVO);

    /**
     * 删除房型价格
     *
     * @param id 编号
     */
    void deleteRoomTypeRate(Long id);

    /**
     * 获得房型价格
     *
     * @param id 编号
     * @return 房型价格
     */
    RoomTypeRateDO getRoomTypeRate(Long id);

    /**
     * 获得房型价格列表
     *
     * @param ids 编号
     * @return 房型价格列表
     */
    List<RoomTypeRateDO> getRoomTypeRateList(Collection<Long> ids);

    /**
     * 获得房型价格分页
     *
     * @param pageReqVO 分页查询
     * @return 房型价格分页
     */
    PageResult<RoomTypeRateDO> getRoomTypeRatePage(RoomTypeRatePageReqVO pageReqVO);

    /**
     * 根据日期获得房型价格
     *
     * @param pageReqVO 分页查询
     * @return 房型价格分页
     */
    ArrayList<RoomTypeRateListRespVO> getRoomTypeRatePageByDate(RoomTypeRateExportReqVO pageReqVO);

    /**
     * 获得当天房型价格
     *
     * @return 房型价格
     */
    List<RoomTypeRateListRespVO> getRoomTypeRatePageByDate();

    /**
     * 获得房型价格列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 房型价格列表
     */
    List<RoomTypeRateDO> getRoomTypeRateList(RoomTypeRateExportReqVO exportReqVO);

}
