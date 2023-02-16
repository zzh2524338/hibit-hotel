package cn.iocoder.yudao.module.hotel.controller.admin.building.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 公司分部 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class BuildingExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("name")
    private String name;

    @ExcelProperty("负责人")
    private String manager;

    @ExcelProperty("电话")
    private String phone;

    @ExcelProperty("总楼层")
    private Integer floorNum;

    @ExcelProperty("地址")
    private String address;

    @ExcelProperty("所属公司")
    private Long companyId;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
