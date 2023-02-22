package cn.iocoder.yudao.module.hotel.convert.guesthistory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hotel.controller.admin.guesthistory.vo.GuestHistoryCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.guesthistory.vo.GuestHistoryExcelVO;
import cn.iocoder.yudao.module.hotel.controller.admin.guesthistory.vo.GuestHistoryRespVO;
import cn.iocoder.yudao.module.hotel.controller.admin.guesthistory.vo.GuestHistoryUpdateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.order.bo.GuestCheckInInformation;
import cn.iocoder.yudao.module.hotel.dal.dataobject.guesthistory.GuestHistoryDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

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

    @Mappings({
            @Mapping(source = "guestId", target = "id"),
            @Mapping(source = "name", target = "guestName"),
            @Mapping(source = "mobile", target = "phone")
    })
    GuestHistoryDO convertList03(GuestCheckInInformation guestCheckInInformation);

    List<GuestHistoryDO> convertList03(List<GuestCheckInInformation> list);

}
