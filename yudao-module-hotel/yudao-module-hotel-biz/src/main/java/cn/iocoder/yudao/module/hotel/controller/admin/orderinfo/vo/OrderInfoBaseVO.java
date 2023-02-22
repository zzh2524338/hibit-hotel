package cn.iocoder.yudao.module.hotel.controller.admin.orderinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 订单 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class OrderInfoBaseVO {

    @Schema(description = "预定人名称", required = true)
    @NotNull(message = "预定人名称不能为空")
    private String bookingPerson;

    @Schema(description = "联系人", required = true, example = "芋艿")
    @NotNull(message = "联系人不能为空")
    private String contactName;

    @Schema(description = "联系电话", required = true)
    @NotNull(message = "联系电话不能为空")
    private String contactNumber;

    @Schema(description = "订单来源", example = "14972")
    private Integer sourceId;

    @Schema(description = "是否担保")
    private Boolean assure;

    @Schema(description = "担保时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime assureTime;

    @Schema(description = "房价类型", required = true, example = "19951")
    @NotNull(message = "房价类型不能为空")
    private Long roomRateTypeId;

    @Schema(description = "入住时间", required = true)
    @NotNull(message = "入住时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime arrivalTime;

    @Schema(description = "离店时间", required = true)
    @NotNull(message = "离店时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime departTime;

    @Schema(description = "是否爆单预定", required = true, example = "2")
    @NotNull(message = "是否爆单预定不能为空")
    private Boolean ignoreRoomStatus;

    @Schema(description = "备注信息", example = "你猜")
    private String remark;

    @Schema(description = "房间信息", required = true)
    @NotNull(message = "房间信息不能为空")
    private String roomInfo;

}
