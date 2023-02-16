package cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客史信息 Excel 导出 Request VO 参数和 GuestInfoPageReqVO 是一致的")
@Data
public class GuestInfoExportReqVO {

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "总入住天数")
    private Integer stayNightNum;

    @Schema(description = "入住次数")
    private Integer checkinNum;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
