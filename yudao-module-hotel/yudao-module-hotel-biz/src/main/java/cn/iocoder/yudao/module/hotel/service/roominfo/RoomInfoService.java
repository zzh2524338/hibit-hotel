package cn.iocoder.yudao.module.hotel.service.roominfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo.RoomInfoCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo.RoomInfoExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo.RoomInfoPageReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo.RoomInfoUpdateReqVO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roominfo.RoomInfoDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 房间信息 Service 接口
 *
 * @author 芋道源码
 */
public interface RoomInfoService {

    /**
     * 创建房间信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRoomInfo(@Valid RoomInfoCreateReqVO createReqVO);

    /**
     * 更新房间信息
     *
     * @param updateReqVO 更新信息
     */
    void updateRoomInfo(@Valid RoomInfoUpdateReqVO updateReqVO);

    /**
     * 删除房间信息
     *
     * @param id 编号
     */
    void deleteRoomInfo(Long id);

    /**
     * 获得房间信息
     *
     * @param id 编号
     * @return 房间信息
     */
    RoomInfoDO getRoomInfo(Long id);

    /**
     * 获得房间信息列表
     *
     * @param ids 编号
     * @return 房间信息列表
     */
    List<RoomInfoDO> getRoomInfoList(Collection<Long> ids);

    /**
     * 获得房间信息分页
     *
     * @param pageReqVO 分页查询
     * @return 房间信息分页
     */
    PageResult<RoomInfoDO> getRoomInfoPage(RoomInfoPageReqVO pageReqVO);

    /**
     * 获得房间信息列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 房间信息列表
     */
    List<RoomInfoDO> getRoomInfoList(RoomInfoExportReqVO exportReqVO);

}
