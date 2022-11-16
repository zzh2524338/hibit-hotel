package cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo;

import lombok.*;

import java.sql.Blob;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 房间类型 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class RoomTypeExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("面积")
    private BigDecimal area;

    @ExcelProperty("最大入住人数")
    private Integer livingPopulation;

    @ExcelProperty("name")
    private String name;

    @ExcelProperty("价格")
    private BigDecimal price;

    @ExcelProperty("描述")
    private String desc;

    @ExcelProperty("图片")
    private String imgs;

    @ExcelProperty("房间总量")
    private Integer totalRoom;

    @ExcelProperty("创建时间")
    private Date createTime;

}
