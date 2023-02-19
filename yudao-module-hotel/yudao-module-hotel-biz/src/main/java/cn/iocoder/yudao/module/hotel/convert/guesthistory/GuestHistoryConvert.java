package cn.iocoder.yudao.module.hotel.convert.guesthistory;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.hotel.controller.admin.guesthistory.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.guesthistory.GuestHistoryDO;

/**
 * 客史信息 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface GuestHistoryConvert {

    GuestHistoryConvert INSTANCE = Mappers.getMapper(GuestHistoryConvert.class);

    GuestHistoryDO convert(GuestHistoryCreateReqVO bean);

    GuestHistoryDO convert(GuestHistoryUpdateReqVO bean);

    GuestHistoryRespVO convert(GuestHistoryDO bean);

    List<GuestHistoryRespVO> convertList(List<GuestHistoryDO> list);

    PageResult<GuestHistoryRespVO> convertPage(PageResult<GuestHistoryDO> page);

    List<GuestHistoryExcelVO> convertList02(List<GuestHistoryDO> list);

}
