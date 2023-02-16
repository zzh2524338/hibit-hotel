package cn.iocoder.yudao.module.hotel.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 订单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class OrderInfoBaseVO {

    @Schema(description = "订单id")
    // @NotNull(message = "订单Id不能为空", groups = UpdateReq.class)
    private Long id;

    @Schema(description = "订单uuid")
    // @NotBlank(message = "订单uuid不能为空", groups = UpdateReq.class)
    private String uuid;

    @Schema(description = "预定人名称", required = true)
    @NotNull(message = "预定人名称不能为空")
    private String orderGuestName;

    @Schema(description = "联系人", required = true)
    @NotNull(message = "联系人不能为空")
    private String contactPerson;

    @Schema(description = "联系电话", required = true)
    @NotNull(message = "联系电话不能为空")
    private String contactNumber;

    @Schema(description = "房间号", required = true)
    @NotNull(message = "房间号不能为空")
    private String roomNo;

    @Schema(description = "房间类型", required = true)
    @NotNull(message = "房间类型Id不能为空")
    private Long roomTypeId;

    @Schema(description = "会员信息", required = true)
    private Long memberId;

    @Schema(description = "订单来源", required = true)
    @NotNull(message = "订单来源不能为空")
    private Long guestSourceId;

    @Schema(description = "入住时间", required = true)
    @NotNull(message = "入住时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date checkInTime;

    @Schema(description = "离店时间", required = true)
    @NotNull(message = "离店时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date checkOutTime;

}
