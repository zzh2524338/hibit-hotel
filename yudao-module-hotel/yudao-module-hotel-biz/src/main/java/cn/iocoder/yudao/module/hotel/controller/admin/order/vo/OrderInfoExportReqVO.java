package cn.iocoder.yudao.module.hotel.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 订单 Excel 导出 Request VO", description = "参数和 OrderInfoPageReqVO 是一致的")
@Data
public class OrderInfoExportReqVO {

    @ApiModelProperty(value = "订单号")
    private String uuid;

    @ApiModelProperty(value = "预定人名称")
    private String orderGuestName;

    @ApiModelProperty(value = "联系人")
    private String contactPerson;

    @ApiModelProperty(value = "联系电话")
    private String contactNumber;

    @ApiModelProperty(value = "房间信息")
    private String roomInfo;

    @ApiModelProperty(value = "会员信息快照")
    private String memberInfo;

    @ApiModelProperty(value = "订单来源")
    private Long guestSourceId;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "入住时间")
    private Date checkInTime;

    @ApiModelProperty(value = "离店时间")
    private Date checkOutTime;

}
