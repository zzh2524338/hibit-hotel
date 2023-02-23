package cn.iocoder.yudao.module.hotel.controller.admin.folioinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 房单信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FolioInfoPageReqVO extends PageParam {

    @Schema(description = "订单号", example = "26788")
    private Long orderId;

    @Schema(description = "房间类型", example = "23166")
    private Long roomTypeId;

    @Schema(description = "房间信息", example = "25265")
    private Long roomId;

    @Schema(description = "房单状态", example = "1")
    private Short status;

    @Schema(description = "到店时间")
    private LocalDateTime arrivalTime;

    @Schema(description = "离店时间")
    private LocalDateTime departTime;

}
