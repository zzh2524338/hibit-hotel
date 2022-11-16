package cn.iocoder.yudao.module.hotel.convert.roomtype;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtype.RoomTypeDO;

/**
 * 房间类型 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface RoomTypeConvert {

    RoomTypeConvert INSTANCE = Mappers.getMapper(RoomTypeConvert.class);

    RoomTypeDO convert(RoomTypeCreateReqVO bean);

    RoomTypeDO convert(RoomTypeUpdateReqVO bean);

    RoomTypeRespVO convert(RoomTypeDO bean);

    List<RoomTypeRespVO> convertList(List<RoomTypeDO> list);

    PageResult<RoomTypeRespVO> convertPage(PageResult<RoomTypeDO> page);

    List<RoomTypeExcelVO> convertList02(List<RoomTypeDO> list);

}
