package cn.iocoder.yudao.module.hotel.controller.admin.orderinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 订单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderInfoRespVO extends OrderInfoBaseVO {

    @Schema(description = "id", required = true, example = "7585")
    private Long id;

    @Schema(description = "订单状态", required = true, example = "1")
    private Integer status;

}
