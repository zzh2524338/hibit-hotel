package cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 会员等级 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class MemberLevelExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("等级名称")
    private String name;

    @ExcelProperty("会员等级")
    private Integer level;

    @ExcelProperty("等级开始值")
    private Integer startPoints;

    @ExcelProperty("等级结束值")
    private Integer endPoints;

    @ExcelProperty("折扣")
    private Integer discount;

    @ExcelProperty("创建时间")
    private Date createTime;

}
