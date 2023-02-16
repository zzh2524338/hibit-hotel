package cn.iocoder.yudao.module.hotel.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 订单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderInfoUpdateReqVO extends OrderInfoBaseVO {

    @Schema(description = "id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

    @Schema(description = "中介号")
    private String intermediaryNumber;

    @Schema(description = "备注信息")
    private String remark;

    @Schema(description = "原价", required = true)
    @NotNull(message = "原价不能为空")
    private BigDecimal originalPrice;

    @Schema(description = "折后实际价格", required = true)
    @NotNull(message = "折后实际价格不能为空")
    private BigDecimal discountPrice;

    @Schema(description = "实际付款", required = true)
    @NotNull(message = "实际付款不能为空")
    private BigDecimal actuallyPaid;

    @Schema(description = "订单状态", required = true)
    @NotNull(message = "订单状态不能为空")
    private Integer orderStatus;

    @Schema(description = "客人信息", required = true)
    @NotNull(message = "客人信息不能为空")
    private String guestInfo;

}
