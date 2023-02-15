package cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 房型管理 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class RoomTypeExcelVO {

    @ExcelProperty("id 主键")
    private Long id;

    @ExcelProperty("房型名称")
    private String name;

    @ExcelProperty("房型编号")
    private String code;

    @ExcelProperty("大床数量")
    private Integer bedCount;

    @ExcelProperty("入住人数")
    private Integer maxCheckInCount;

    @ExcelProperty("备注")
    private String description;

    @ExcelProperty("排序")
    private Integer sort;

    @ExcelProperty("面积")
    private BigDecimal area;

    @ExcelProperty("控房数")
    private Integer roomControl;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
