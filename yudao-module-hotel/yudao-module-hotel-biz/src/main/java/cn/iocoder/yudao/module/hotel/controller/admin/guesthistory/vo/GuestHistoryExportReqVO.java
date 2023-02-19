package cn.iocoder.yudao.module.hotel.controller.admin.guesthistory.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客史信息 Excel 导出 Request VO，参数和 GuestHistoryPageReqVO 是一致的")
@Data
public class GuestHistoryExportReqVO {

    @Schema(description = "客人姓名", example = "王五")
    private String guestName;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "证件编号")
    private String cardNo;

    @Schema(description = "会员编号", example = "6647")
    private Long memberId;

    @Schema(description = "最近一次入住")
    private LocalDateTime latestCheckin;

    @Schema(description = "入住次数")
    private Integer checkinNum;

}
