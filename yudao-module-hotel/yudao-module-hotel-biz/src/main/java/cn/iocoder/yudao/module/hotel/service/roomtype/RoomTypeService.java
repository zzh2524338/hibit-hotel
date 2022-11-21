package cn.iocoder.yudao.module.hotel.service.roomtype;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtype.RoomTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 房间类型 Service 接口
 *
 * @author 芋道源码
 */
public interface RoomTypeService {

    /**
     * 创建房间类型
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRoomType(@Valid RoomTypeCreateReqVO createReqVO);

    /**
     * 更新房间类型
     *
     * @param updateReqVO 更新信息
     */
    void updateRoomType(@Valid RoomTypeUpdateReqVO updateReqVO);

    /**
     * 删除房间类型
     *
     * @param id 编号
     */
    void deleteRoomType(Long id);

    /**
     * 获得房间类型
     *
     * @param id 编号
     * @return 房间类型
     */
    RoomTypeDO getRoomType(Long id);

    /**
     * 获得房间类型列表
     *
     * @param ids 编号
     * @return 房间类型列表
     */
    List<RoomTypeDO> getRoomTypeListByIds(Collection<Long> ids);

    /**
     * 获得房间类型列表
     *
     * @return 房间类型列表
     */
    List<RoomTypeDO> getRoomTypeList();

    /**
     * 获得房间类型分页
     *
     * @param pageReqVO 分页查询
     * @return 房间类型分页
     */
    PageResult<RoomTypeDO> getRoomTypePage(RoomTypePageReqVO pageReqVO);

    /**
     * 获得房间类型列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 房间类型列表
     */
    List<RoomTypeDO> getRoomTypeList(RoomTypeExportReqVO exportReqVO);

}
