package cn.iocoder.yudao.module.hotel.service.roomratetype;

import cn.hutool.core.util.IdUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hotel.controller.admin.roomratetype.vo.RoomRateTypeCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomratetype.vo.RoomRateTypeExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomratetype.vo.RoomRateTypePageReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomratetype.vo.RoomRateTypeUpdateReqVO;
import cn.iocoder.yudao.module.hotel.convert.roomratetype.RoomRateTypeConvert;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomratetype.RoomRateTypeDO;
import cn.iocoder.yudao.module.hotel.dal.mysql.roomratetype.RoomRateTypeMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.ROOM_RATE_TYPE_NOT_EXISTS;

/**
 * 房价类型 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RoomRateTypeServiceImpl implements RoomRateTypeService {

    @Resource
    private RoomRateTypeMapper roomRateTypeMapper;

    @Override
    public Long createRoomRateType(RoomRateTypeCreateReqVO createReqVO) {
        // 插入
        RoomRateTypeDO roomRateType = RoomRateTypeConvert.INSTANCE.convert(createReqVO);
        roomRateType.setUuid("room_rate_type_" + IdUtil.getSnowflake());
        roomRateTypeMapper.insert(roomRateType);
        // 返回
        return roomRateType.getId();
    }

    @Override
    public void updateRoomRateType(RoomRateTypeUpdateReqVO updateReqVO) {
        // 校验存在
        validateRoomRateTypeExists(updateReqVO.getId());
        // 更新
        RoomRateTypeDO updateObj = RoomRateTypeConvert.INSTANCE.convert(updateReqVO);
        roomRateTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteRoomRateType(Long id) {
        // 校验存在
        validateRoomRateTypeExists(id);
        // 删除
        roomRateTypeMapper.deleteById(id);
    }

    private void validateRoomRateTypeExists(Long id) {
        if (roomRateTypeMapper.selectById(id) == null) {
            throw exception(ROOM_RATE_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public RoomRateTypeDO getRoomRateType(Long id) {
        return roomRateTypeMapper.selectById(id);
    }

    @Override
    public List<RoomRateTypeDO> getRoomRateTypeList(Collection<Long> ids) {
        return roomRateTypeMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<RoomRateTypeDO> getRoomRateTypePage(RoomRateTypePageReqVO pageReqVO) {
        return roomRateTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RoomRateTypeDO> getRoomRateTypeList(RoomRateTypeExportReqVO exportReqVO) {
        return roomRateTypeMapper.selectList(exportReqVO);
    }

}
