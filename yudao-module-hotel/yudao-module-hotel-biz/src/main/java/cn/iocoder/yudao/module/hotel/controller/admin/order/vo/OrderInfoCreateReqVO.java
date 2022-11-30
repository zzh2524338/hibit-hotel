package cn.iocoder.yudao.module.hotel.controller.admin.order.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;

import io.swagger.annotations.*;

import javax.validation.constraints.*;

@ApiModel("管理后台 - 订单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderInfoCreateReqVO extends OrderInfoBaseVO {

    @ApiModelProperty(value = "中介号")
    private String intermediaryNumber;

    @ApiModelProperty(value = "备注信息")
    private String remark;

    @ApiModelProperty(value = "原价", required = true)
    @NotNull(message = "原价不能为空")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "折后实际价格", required = true)
    @NotNull(message = "折后实际价格不能为空")
    private BigDecimal discountPrice;

    @ApiModelProperty(value = "实际付款", required = true)
    @NotNull(message = "实际付款不能为空")
    private BigDecimal actuallyPaid;

    @ApiModelProperty(value = "客人信息", required = true)
    @NotNull(message = "客人信息不能为空")
    private String guestInfo;

}
