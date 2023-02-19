package cn.iocoder.yudao.module.hotel.service.roomratetype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hotel.controller.admin.roomratetype.vo.RoomRateTypeCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomratetype.vo.RoomRateTypeExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomratetype.vo.RoomRateTypePageReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomratetype.vo.RoomRateTypeUpdateReqVO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomratetype.RoomRateTypeDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 房价类型 Service 接口
 *
 * @author 芋道源码
 */
public interface RoomRateTypeService {

    /**
     * 创建房价类型
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRoomRateType(@Valid RoomRateTypeCreateReqVO createReqVO);

    /**
     * 更新房价类型
     *
     * @param updateReqVO 更新信息
     */
    void updateRoomRateType(@Valid RoomRateTypeUpdateReqVO updateReqVO);

    /**
     * 删除房价类型
     *
     * @param id 编号
     */
    void deleteRoomRateType(Long id);

    /**
     * 获得房价类型
     *
     * @param id 编号
     * @return 房价类型
     */
    RoomRateTypeDO getRoomRateType(Long id);

    /**
     * 获得房价类型列表
     *
     * @param ids 编号
     * @return 房价类型列表
     */
    List<RoomRateTypeDO> getRoomRateTypeList(Collection<Long> ids);

    /**
     * 获得房价类型列表
     *
     * @return 房价类型列表
     */
    List<RoomRateTypeDO> getRoomRateTypeList();

    /**
     * 获得房价类型分页
     *
     * @param pageReqVO 分页查询
     * @return 房价类型分页
     */
    PageResult<RoomRateTypeDO> getRoomRateTypePage(RoomRateTypePageReqVO pageReqVO);

    /**
     * 获得房价类型列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 房价类型列表
     */
    List<RoomRateTypeDO> getRoomRateTypeList(RoomRateTypeExportReqVO exportReqVO);

}
