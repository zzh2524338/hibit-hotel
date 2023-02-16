package cn.iocoder.yudao.module.hotel.service.roominfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo.RoomInfoCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo.RoomInfoExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo.RoomInfoPageReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo.RoomInfoUpdateReqVO;
import cn.iocoder.yudao.module.hotel.convert.roominfo.RoomInfoConvert;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roominfo.RoomInfoDO;
import cn.iocoder.yudao.module.hotel.dal.mysql.roominfo.RoomInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.ROOM_INFO_NOT_EXISTS;

/**
 * 房间信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RoomInfoServiceImpl implements RoomInfoService {

    @Resource
    private RoomInfoMapper roomInfoMapper;

    @Override
    public Long createRoomInfo(RoomInfoCreateReqVO createReqVO) {
        // 插入
        RoomInfoDO roomInfo = RoomInfoConvert.INSTANCE.convert(createReqVO);
        roomInfoMapper.insert(roomInfo);
        // 返回
        return roomInfo.getId();
    }

    @Override
    public void updateRoomInfo(RoomInfoUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateRoomInfoExists(updateReqVO.getId());
        // 更新
        RoomInfoDO updateObj = RoomInfoConvert.INSTANCE.convert(updateReqVO);
        roomInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteRoomInfo(Long id) {
        // 校验存在
        this.validateRoomInfoExists(id);
        // 删除
        roomInfoMapper.deleteById(id);
    }

    private void validateRoomInfoExists(Long id) {
        if (roomInfoMapper.selectById(id) == null) {
            throw exception(ROOM_INFO_NOT_EXISTS);
        }
    }

    @Override
    public RoomInfoDO getRoomInfo(Long id) {
        return roomInfoMapper.selectById(id);
    }

    @Override
    public List<RoomInfoDO> getRoomInfoList(Collection<Long> ids) {
        return roomInfoMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<RoomInfoDO> getRoomInfoPage(RoomInfoPageReqVO pageReqVO) {
        return roomInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RoomInfoDO> getRoomInfoList(RoomInfoExportReqVO exportReqVO) {
        return roomInfoMapper.selectList(exportReqVO);
    }

}
