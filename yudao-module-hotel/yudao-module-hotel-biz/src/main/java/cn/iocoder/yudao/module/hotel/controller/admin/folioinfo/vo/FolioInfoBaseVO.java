package cn.iocoder.yudao.module.hotel.controller.admin.folioinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 房单信息 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class FolioInfoBaseVO {

    @Schema(description = "房间类型", required = true, example = "23166")
    @NotNull(message = "房间类型不能为空")
    private Long roomTypeId;

    @Schema(description = "房间信息", required = true, example = "25265")
    @NotNull(message = "房间信息不能为空")
    private Long roomId;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "客人信息")
    private String guestInfo;

    @Schema(description = "到店时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime arrivalTime;

    @Schema(description = "离店时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime departTime;

}
