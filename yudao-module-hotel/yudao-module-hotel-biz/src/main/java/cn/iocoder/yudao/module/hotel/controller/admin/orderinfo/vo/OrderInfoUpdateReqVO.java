package cn.iocoder.yudao.module.hotel.controller.admin.orderinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 订单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderInfoUpdateReqVO extends OrderInfoBaseVO {

    @Schema(description = "id", required = true, example = "7585")
    @NotNull(message = "id不能为空")
    private Long id;

    @Schema(description = "订单号", required = true, example = "3034")
    @NotNull(message = "订单号不能为空")
    private String uuid;

    @Schema(description = "订单状态", required = true, example = "1")
    @NotNull(message = "订单状态不能为空")
    private Integer status;

    @Schema(description = "中介号")
    private String agencyCode;

    @Schema(description = "客人信息")
    private String bookingGuestInfo;

    @Schema(description = "房单", example = "11441")
    private Long folioId;

    @Schema(description = "房单类型", example = "1")
    private Integer folioType;

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
