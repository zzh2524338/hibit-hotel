package cn.iocoder.yudao.module.hotel.controller.admin.order.bo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "酒店管理后台 - 订单创建中的客人信息")
@Data
@ToString(callSuper = true)
public class OrderInfoBookGuest {
    @Schema(description = "房间类型编号")
    @NotNull(message = "房间不能为空，请选择")
    private Long roomTypeId;

    @Schema(description = "房间数量")
    private Integer roomCount;

    @Schema(description = "早餐数量")
    private Integer breakfastCount = 0;

    @Schema(description = "是否忽略房间状态")
    private Boolean ignoreRoomStatus;

    @Schema(description = "房间号")
    private String roomNo;

    @Schema(description = "房间价格")
    private String roomRate;

    @Schema(description = "到店时间")
    @NotNull(message = "到店时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime arrivalTime;

    @Schema(description = "离店时间")
    @NotNull(message = "离店时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime departTime;
    @Schema(description = "入住人信息")
    private List<GuestCheckInInformation> guestCheckInInfoList;
}
