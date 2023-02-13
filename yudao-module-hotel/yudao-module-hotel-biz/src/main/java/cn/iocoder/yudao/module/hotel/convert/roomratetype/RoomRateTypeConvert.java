package cn.iocoder.yudao.module.hotel.convert.roomratetype;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.hotel.controller.admin.roomratetype.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomratetype.RoomRateTypeDO;

/**
 * 房价类型 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface RoomRateTypeConvert {

    RoomRateTypeConvert INSTANCE = Mappers.getMapper(RoomRateTypeConvert.class);

    RoomRateTypeDO convert(RoomRateTypeCreateReqVO bean);

    RoomRateTypeDO convert(RoomRateTypeUpdateReqVO bean);

    RoomRateTypeRespVO convert(RoomRateTypeDO bean);

    List<RoomRateTypeRespVO> convertList(List<RoomRateTypeDO> list);

    PageResult<RoomRateTypeRespVO> convertPage(PageResult<RoomRateTypeDO> page);

    List<RoomRateTypeExcelVO> convertList02(List<RoomRateTypeDO> list);

}
