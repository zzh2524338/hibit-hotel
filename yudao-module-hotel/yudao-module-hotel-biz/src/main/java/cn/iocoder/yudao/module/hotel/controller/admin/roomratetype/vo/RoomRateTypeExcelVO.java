package cn.iocoder.yudao.module.hotel.controller.admin.roomratetype.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 房价类型 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class RoomRateTypeExcelVO {

    @ExcelProperty(" 编号")
    private Long id;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
