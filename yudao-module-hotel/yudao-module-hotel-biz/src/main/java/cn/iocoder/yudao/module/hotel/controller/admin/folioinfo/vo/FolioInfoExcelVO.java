package cn.iocoder.yudao.module.hotel.controller.admin.folioinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 房单信息 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class FolioInfoExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("订单号")
    private Long orderId;

    @ExcelProperty("房间类型")
    private Long roomTypeId;

    @ExcelProperty("房间信息")
    private Long roomId;

    @ExcelProperty("房单状态")
    private Short status;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("客人信息")
    private String guestInfo;

    @ExcelProperty("到店时间")
    private LocalDateTime arrivalTime;

    @ExcelProperty("离店时间")
    private LocalDateTime departTime;

}
