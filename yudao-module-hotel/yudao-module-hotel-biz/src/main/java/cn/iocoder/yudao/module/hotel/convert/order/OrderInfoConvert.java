package cn.iocoder.yudao.module.hotel.convert.order;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hotel.controller.admin.order.vo.OrderInfoCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.order.vo.OrderInfoExcelVO;
import cn.iocoder.yudao.module.hotel.controller.admin.order.vo.OrderInfoRespVO;
import cn.iocoder.yudao.module.hotel.controller.admin.order.vo.OrderInfoUpdateReqVO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.order.OrderInfoDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

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

}
