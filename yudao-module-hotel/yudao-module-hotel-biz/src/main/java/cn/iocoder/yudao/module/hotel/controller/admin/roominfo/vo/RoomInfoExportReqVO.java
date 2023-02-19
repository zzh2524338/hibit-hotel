package cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 房间信息 Excel 导出 Request VO 参数和 RoomInfoPageReqVO 是一致的")
@Data
public class RoomInfoExportReqVO {

    @Schema(description = "房间号")
    private String no;

    @Schema(description = "房间类型表")
    private Integer roomType;

    @Schema(description = "楼层")
    private Integer floor;

    @Schema(description = "清洁状态")
    private Integer cleanStatus;

    @Schema(description = "房间钥匙信息")
    private String keyInfo;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
