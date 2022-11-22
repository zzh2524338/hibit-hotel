package cn.iocoder.yudao.module.hotel.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 订单 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class OrderInfoBaseVO {

    @ApiModelProperty(value = "预定人名称", required = true)
    @NotNull(message = "预定人名称不能为空")
    private String orderGuestName;

    @ApiModelProperty(value = "联系人", required = true)
    @NotNull(message = "联系人不能为空")
    private String contactPerson;

    @ApiModelProperty(value = "联系电话", required = true)
    @NotNull(message = "联系电话不能为空")
    private String contactNumber;

    @ApiModelProperty(value = "房间信息", required = true)
    @NotNull(message = "房间信息不能为空")
    private String roomInfo;

    @ApiModelProperty(value = "会员信息快照", required = true)
    @NotNull(message = "会员信息快照不能为空")
    private String memberInfo;

    @ApiModelProperty(value = "订单来源", required = true)
    @NotNull(message = "订单来源不能为空")
    private Long guestSourceId;

    @ApiModelProperty(value = "入住时间", required = true)
    @NotNull(message = "入住时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date checkInTime;

    @ApiModelProperty(value = "离店时间", required = true)
    @NotNull(message = "离店时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date checkOutTime;

}
