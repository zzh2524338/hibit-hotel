package cn.iocoder.yudao.module.hotel.controller.admin.orderinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 订单 Excel 导出 Request VO，参数和 OrderInfoPageReqVO 是一致的")
@Data
public class OrderInfoExportReqVO {

    @Schema(description = "订单状态", example = "1")
    private Integer status;

    @Schema(description = "预定人名称")
    private String bookingPerson;

    @Schema(description = "联系人", example = "芋艿")
    private String contactName;

    @Schema(description = "联系电话")
    private String contactNumber;

    @Schema(description = "是否担保")
    private Boolean assure;

    @Schema(description = "担保时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] assureTime;

    @Schema(description = "入住时间")
    private LocalDateTime arrivalTime;

    @Schema(description = "离店时间")
    private LocalDateTime departTime;

    @Schema(description = "会员信息快照")
    private String memberInfo;

}
