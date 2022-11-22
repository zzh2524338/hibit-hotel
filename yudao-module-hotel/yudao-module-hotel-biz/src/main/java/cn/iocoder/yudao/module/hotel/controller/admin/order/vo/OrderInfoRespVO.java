package cn.iocoder.yudao.module.hotel.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 订单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderInfoRespVO extends OrderInfoBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "订单号", required = true)
    private String uuid;

    @ApiModelProperty(value = "订单状态", required = true)
    private Integer orderStatus;

}
