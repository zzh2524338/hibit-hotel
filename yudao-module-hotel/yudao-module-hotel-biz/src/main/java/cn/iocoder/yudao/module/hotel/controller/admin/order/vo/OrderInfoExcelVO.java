package cn.iocoder.yudao.module.hotel.controller.admin.order.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 订单 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class OrderInfoExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("订单号")
    private String uuid;

    @ExcelProperty("预定人名称")
    private String orderGuestName;

    @ExcelProperty("联系人")
    private String contactPerson;

    @ExcelProperty("联系电话")
    private String contactNumber;

    @ExcelProperty("房间信息")
    private String roomInfo;

    @ExcelProperty("会员信息快照")
    private String memberInfo;

    @ExcelProperty("订单来源")
    private Long guestSourceId;

    @ExcelProperty("订单状态")
    private Integer orderStatus;

    @ExcelProperty("入住时间")
    private Date checkInTime;

    @ExcelProperty("离店时间")
    private Date checkOutTime;

}
