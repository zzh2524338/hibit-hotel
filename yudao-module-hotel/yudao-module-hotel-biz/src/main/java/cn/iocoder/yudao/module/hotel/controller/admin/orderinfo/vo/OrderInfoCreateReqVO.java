package cn.iocoder.yudao.module.hotel.controller.admin.orderinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 订单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderInfoCreateReqVO extends OrderInfoBaseVO {

    @Schema(description = "中介号")
    private String agencyCode;

    @Schema(description = "客人信息")
    private String bookingGuestInfo;

    @Schema(description = "订单来源(小类)", required = true, example = "19550")
    @NotNull(message = "订单来源(小类)不能为空")
    private Integer sourceSubId;

    @Schema(description = "客源", example = "18321")
    private Integer guestsSourceId;

    @Schema(description = "客源(小类)", example = "25591")
    private Integer guestsSubSourceId;

    @Schema(description = "订单来源", required = true, example = "6866")
    @NotNull(message = "订单来源不能为空")
    private Integer guestSourceId;

    @Schema(description = "会员信息快照", required = true)
    @NotNull(message = "会员信息快照不能为空")
    private String memberInfo;

}
