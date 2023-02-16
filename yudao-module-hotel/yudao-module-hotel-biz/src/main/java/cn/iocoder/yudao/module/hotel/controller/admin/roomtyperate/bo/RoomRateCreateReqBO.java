package cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.bo;


import cn.hutool.core.date.Week;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;

/**
 * 房价调整接口
 */
@Data
public class RoomRateCreateReqBO {

    @Schema(description = "房型编号", required = true, example = "5239")
    @NotNull(message = "房型价格列表不能为空")
    private List<RoomRate> roomRateList;

    @Schema(description = "开始日期", required = true, example = "2022-02-16")
    @NotNull(message = "开始日期不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY)
    private LocalDate startDate;

    @Schema(description = "结束日期", required = true, example = "2022-02-16")
    @NotNull(message = "结束日期不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY)
    private LocalDate endDate;

    @Schema(description = "定义的周末", example = "[SUNDAY,MONDAY]")
    private Week[] weekDay;

    @Schema(description = "保留小数位数", example = "[1,2,3]")
    private Integer retainNo;

    @Data
    public static class RoomRate {

        @Schema(description = "房型编号", example = "12")
        @NotNull(message = "房型编号不能为空")
        private Long roomTypeId;

        @Schema(description = "房价类型编号", example = "12")
        @NotNull(message = "房价类型编号不能为空")
        private Long roomRateTypeId;

        @Schema(description = "房价", example = "12")
        @NotNull(message = "房价不能为空")
        private String rate;

        @Schema(description = "周末房价", example = "12")
        @NotNull(message = "周末房价不能为空")
        private String weekRate;
    }
}
