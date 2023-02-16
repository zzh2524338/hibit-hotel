package cn.iocoder.yudao.module.hotel.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 订单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderInfoRespVO extends OrderInfoBaseVO {

    @Schema(description = "id", required = true)
    private Long id;

    @Schema(description = "订单号", required = true)
    private String uuid;

    @Schema(description = "订单状态", required = true)
    private Integer orderStatus;

}
