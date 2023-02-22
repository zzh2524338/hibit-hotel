package cn.iocoder.yudao.module.hotel.controller.admin.orderinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 订单 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class OrderInfoExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("订单状态")
    private Integer status;

    @ExcelProperty("预定人名称")
    private String bookingPerson;

    @ExcelProperty("联系人")
    private String contactName;

    @ExcelProperty("联系电话")
    private String contactNumber;

    @ExcelProperty("订单来源")
    private Integer sourceId;

    @ExcelProperty("是否担保")
    private Boolean assure;

    @ExcelProperty("担保时间")
    private LocalDateTime assureTime;

    @ExcelProperty("房价类型")
    private Long roomRateTypeId;

    @ExcelProperty("入住时间")
    private LocalDateTime arrivalTime;

    @ExcelProperty("离店时间")
    private LocalDateTime departTime;

    @ExcelProperty("是否爆单预定")
    private Boolean ignoreRoomStatus;

    @ExcelProperty("备注信息")
    private String remark;

    @ExcelProperty("房间信息")
    private String roomInfo;

}
