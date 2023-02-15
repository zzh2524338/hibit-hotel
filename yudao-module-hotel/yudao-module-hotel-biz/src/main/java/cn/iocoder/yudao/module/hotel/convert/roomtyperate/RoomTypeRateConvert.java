package cn.iocoder.yudao.module.hotel.convert.roomtyperate;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtyperate.RoomTypeRateDO;

/**
 * 房型价格 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface RoomTypeRateConvert {

    RoomTypeRateConvert INSTANCE = Mappers.getMapper(RoomTypeRateConvert.class);

    RoomTypeRateDO convert(RoomTypeRateCreateReqVO bean);

    RoomTypeRateDO convert(RoomTypeRateUpdateReqVO bean);

    RoomTypeRateRespVO convert(RoomTypeRateDO bean);

    List<RoomTypeRateRespVO> convertList(List<RoomTypeRateDO> list);

    PageResult<RoomTypeRateRespVO> convertPage(PageResult<RoomTypeRateDO> page);

    List<RoomTypeRateExcelVO> convertList02(List<RoomTypeRateDO> list);

}
