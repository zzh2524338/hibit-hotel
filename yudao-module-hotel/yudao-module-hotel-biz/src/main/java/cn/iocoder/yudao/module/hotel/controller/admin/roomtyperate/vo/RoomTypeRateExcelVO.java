package cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 房型价格 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class RoomTypeRateExcelVO {

    @ExcelProperty(" 编号")
    private Long id;

    @ExcelProperty("房型编号")
    private Long roomTypeId;

    @ExcelProperty("房价类型编号")
    private Long roomRateTypeId;

    @ExcelProperty("现在折扣价格")
    private BigDecimal roomRate;

    @ExcelProperty("门市价格")
    private BigDecimal proRate;

    @ExcelProperty("生效日期")
    private LocalDateTime accDate;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
