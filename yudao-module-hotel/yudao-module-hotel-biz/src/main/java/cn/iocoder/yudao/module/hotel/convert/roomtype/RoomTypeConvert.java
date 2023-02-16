package cn.iocoder.yudao.module.hotel.convert.roomtype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.RoomTypeCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.RoomTypeExcelVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.RoomTypeRespVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.RoomTypeUpdateReqVO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtype.RoomTypeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 房型管理 Convert
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
