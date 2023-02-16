package cn.iocoder.yudao.module.hotel.controller.admin.order.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Schema(description = "管理后台 - 订单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderInfoPageReqVO extends PageParam {

    @Schema(description = "订单号")
    private String uuid;

    @Schema(description = "预定人名称")
    private String orderGuestName;

    @Schema(description = "联系人")
    private String contactPerson;

    @Schema(description = "联系电话")
    private String contactNumber;

    @Schema(description = "房间信息")
    private String roomInfo;

    @Schema(description = "会员信息快照")
    private String memberInfo;

    @Schema(description = "订单来源")
    private Long guestSourceId;

    @Schema(description = "订单状态")
    private Integer orderStatus;

    @Schema(description = "入住时间")
    private Date checkInTime;

    @Schema(description = "离店时间")
    private Date checkOutTime;

}
