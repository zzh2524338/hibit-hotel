package cn.iocoder.yudao.module.hotel.controller.admin.guesthistory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 客史信息 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class GuestHistoryBaseVO {

    @Schema(description = "客人姓名", required = true, example = "王五")
    @NotNull(message = "客人姓名不能为空")
    private String guestName;

    @Schema(description = "电话", required = true)
    @NotNull(message = "电话不能为空")
    private String phone;

    @Schema(description = "性别", required = true)
    @NotNull(message = "性别不能为空")
    private Integer gender;

    @Schema(description = "证件编号")
    private String cardNo;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "会员编号", example = "6647")
    private Long memberId;

    @Schema(description = "最近一次入住", required = true)
    @NotNull(message = "最近一次入住不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime latestCheckin;

    @Schema(description = "入住次数", required = true)
    @NotNull(message = "入住次数不能为空")
    private Integer checkinNum;

}
