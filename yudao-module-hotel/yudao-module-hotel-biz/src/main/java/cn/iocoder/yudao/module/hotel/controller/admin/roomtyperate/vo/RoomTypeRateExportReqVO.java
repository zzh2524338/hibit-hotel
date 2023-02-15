package cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 房型价格 Excel 导出 Request VO，参数和 RoomTypeRatePageReqVO 是一致的")
@Data
public class RoomTypeRateExportReqVO {

    @Schema(description = "房型编号", example = "5239")
    private Long roomTypeId;

    @Schema(description = "房价类型编号", example = "4867")
    private Long roomRateTypeId;

    @Schema(description = "现在折扣价格")
    private BigDecimal roomRate;

    @Schema(description = "门市价格")
    private BigDecimal proRate;

    @Schema(description = "生效日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] accDate;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
