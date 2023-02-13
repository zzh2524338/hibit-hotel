package cn.iocoder.yudao.module.hotel.service.roomtype;

import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtype.RoomTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.hotel.convert.roomtype.RoomTypeConvert;
import cn.iocoder.yudao.module.hotel.dal.mysql.roomtype.RoomTypeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.*;

/**
 * 房型管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RoomTypeServiceImpl implements RoomTypeService {

    @Resource
    private RoomTypeMapper roomTypeMapper;

    @Override
    public Long createRoomType(RoomTypeCreateReqVO createReqVO) {
        // 插入
        RoomTypeDO roomType = RoomTypeConvert.INSTANCE.convert(createReqVO);

        // 设置默认属性
        roomType.setUuid("room_type_" + IdUtil.getSnowflake());
        roomTypeMapper.insert(roomType);
        // 返回
        return roomType.getId();
    }

    @Override
    public void updateRoomType(RoomTypeUpdateReqVO updateReqVO) {
        // 校验存在
        validateRoomTypeExists(updateReqVO.getId());
        // 更新
        RoomTypeDO updateObj = RoomTypeConvert.INSTANCE.convert(updateReqVO);
        roomTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteRoomType(Long id) {
        // 校验存在
        validateRoomTypeExists(id);
        // 删除
        roomTypeMapper.deleteById(id);
    }

    private void validateRoomTypeExists(Long id) {
        if (roomTypeMapper.selectById(id) == null) {
            throw exception(ROOM_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public RoomTypeDO getRoomType(Long id) {
        return roomTypeMapper.selectById(id);
    }

    @Override
    public List<RoomTypeDO> getRoomTypeList(Collection<Long> ids) {
        return roomTypeMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<RoomTypeDO> getRoomTypePage(RoomTypePageReqVO pageReqVO) {
        return roomTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RoomTypeDO> getRoomTypeList(RoomTypeExportReqVO exportReqVO) {
        return roomTypeMapper.selectList(exportReqVO);
    }

}
