package cn.iocoder.yudao.module.hotel.convert.guestinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo.GuestInfoCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo.GuestInfoExcelVO;
import cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo.GuestInfoRespVO;
import cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo.GuestInfoUpdateReqVO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.guestinfo.GuestInfoDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 客史信息 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface GuestInfoConvert {

    GuestInfoConvert INSTANCE = Mappers.getMapper(GuestInfoConvert.class);

    GuestInfoDO convert(GuestInfoCreateReqVO bean);

    GuestInfoDO convert(GuestInfoUpdateReqVO bean);

    GuestInfoRespVO convert(GuestInfoDO bean);

    List<GuestInfoRespVO> convertList(List<GuestInfoDO> list);

    PageResult<GuestInfoRespVO> convertPage(PageResult<GuestInfoDO> page);

    List<GuestInfoExcelVO> convertList02(List<GuestInfoDO> list);

}
