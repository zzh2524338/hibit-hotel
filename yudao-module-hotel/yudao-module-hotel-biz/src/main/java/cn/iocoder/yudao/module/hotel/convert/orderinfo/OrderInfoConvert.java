package cn.iocoder.yudao.module.hotel.convert.orderinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.hotel.controller.admin.order.bo.OrderInfoBaseBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.hotel.controller.admin.orderinfo.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.orderinfo.OrderInfoDO;

/**
 * 订单 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface OrderInfoConvert {

    OrderInfoConvert INSTANCE = Mappers.getMapper(OrderInfoConvert.class);

    OrderInfoDO convert(OrderInfoCreateReqVO bean);

    OrderInfoDO convert(OrderInfoUpdateReqVO bean);

    OrderInfoRespVO convert(OrderInfoDO bean);

    List<OrderInfoRespVO> convertList(List<OrderInfoDO> list);

    PageResult<OrderInfoRespVO> convertPage(PageResult<OrderInfoDO> page);

    List<OrderInfoExcelVO> convertList02(List<OrderInfoDO> list);

    @Mappings({
                @Mapping(source = "orderNo", target = "id"),
                @Mapping(source = "mobile", target = "contactNumber"),
            }
    )
    OrderInfoDO convert(OrderInfoBaseBO bean);

}
