package cn.iocoder.yudao.module.hotel.convert.roomtyperate;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo.RoomTypeRateCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo.RoomTypeRateExcelVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo.RoomTypeRateRespVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo.RoomTypeRateUpdateReqVO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtyperate.RoomTypeRateDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
