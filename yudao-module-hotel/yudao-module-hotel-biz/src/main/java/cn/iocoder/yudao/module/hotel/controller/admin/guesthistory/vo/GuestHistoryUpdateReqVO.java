package cn.iocoder.yudao.module.hotel.controller.admin.guesthistory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.*;
import javax.validation.constraints.*;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客史信息更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GuestHistoryUpdateReqVO extends GuestHistoryBaseVO {

    @Schema(description = "id", required = true, example = "22459")
    @NotNull(message = "客人id不能为空")
    private Long id;

    @Schema(description = "生日")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate birthday;

    @Schema(description = "身份证", example = "2")
    private Long cardType;

}
