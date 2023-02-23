package cn.iocoder.yudao.module.hotel.controller.admin.order.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "酒店管理后台 - 订单创建更新")
@Data
@ToString(callSuper = true)
public class OrderInfoBaseBO {
    @Schema(description = "订单ID")
    private Long orderNo;

    @Schema(description = "中介ID")
    private String agencyCode;

    @Schema(description = "客人信息")
    private List<OrderInfoBookGuest> orderInfoBookGuests;

    @Schema(description = "房单id")
    private Long folioId;
    @Schema(description = "房单类型")
    private Integer folioType;

    @Schema(description = "订单来源(大类)")
    private Integer sourceId;
    @Schema(description = "订单来源(小类)")
    private Integer subSourceId;

    @Schema(description = "是否担保")
    private Boolean assure;
    @Schema(description = "担保时间")
    private LocalDateTime assureTime;

    @Schema(description = "市场活动id")
    private Long marketId;

    @Schema(description = "会员ID")
    private Long memberId;

    @Schema(description = "房价类型ID")
    @NotNull(message = "房价类型不能为空")
    private Long roomRateTypeId;

    @Schema(description = "到店时间")
    @NotNull(message = "到店时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime arrivalTime;
    @Schema(description = "离店时间")
    @NotNull(message = "离店时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime departTime;

    @Schema(description = "预定人")
    @NotBlank(message = "预定人")
    private String bookingPerson;
    @Schema(description = "联系人")
    @NotBlank(message = "联系人不能为空")
    private String contactName;
    @Schema(description = "联系电话")
    @NotBlank(message = "联系电话不能为空")
    private String mobile;

    @Schema(description = "员工备注")
    private String remark;

    @Schema(description = "客源(大类)")
    @NotNull(message = "客源不能为空(大类)")
    private Integer guestsSourceId;
    @Schema(description = "客源(小类)")
    private Integer guestsSubSourceId;

    @Schema(description = "是否爆单预订(没房仍预定)")
    private Boolean ignoreRoomStatus = false;

    @Schema(description = "销售员Id")
    private Long sellerId;
    @Schema(description = "销售员部门Id")
    private Long sellerDepId;
}
