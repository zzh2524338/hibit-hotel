package cn.iocoder.yudao.module.hotel.convert.roominfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo.RoomInfoCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo.RoomInfoExcelVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo.RoomInfoRespVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo.RoomInfoUpdateReqVO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roominfo.RoomInfoDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 房间信息 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface RoomInfoConvert {

    RoomInfoConvert INSTANCE = Mappers.getMapper(RoomInfoConvert.class);

    RoomInfoDO convert(RoomInfoCreateReqVO bean);

    RoomInfoDO convert(RoomInfoUpdateReqVO bean);

    RoomInfoRespVO convert(RoomInfoDO bean);

    List<RoomInfoRespVO> convertList(List<RoomInfoDO> list);

    PageResult<RoomInfoRespVO> convertPage(PageResult<RoomInfoDO> page);

    List<RoomInfoExcelVO> convertList02(List<RoomInfoDO> list);

}
