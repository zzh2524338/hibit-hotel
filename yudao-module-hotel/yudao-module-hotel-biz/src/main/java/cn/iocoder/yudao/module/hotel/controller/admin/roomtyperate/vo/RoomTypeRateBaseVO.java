package cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 房型价格 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class RoomTypeRateBaseVO {

    @Schema(description = "房型编号", required = true, example = "5239")
    @NotNull(message = "房型编号不能为空")
    private Long roomTypeId;

    @Schema(description = "房价类型编号", required = true, example = "4867")
    @NotNull(message = "房价类型编号不能为空")
    private Long roomRateTypeId;

    @Schema(description = "现在折扣价格", required = true)
    @NotNull(message = "现在折扣价格不能为空")
    private BigDecimal roomRate;

    @Schema(description = "生效日期", required = true)
    @NotNull(message = "生效日期不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime accDate;

}
