package cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员信息 Excel VO
 *
 * @author hibit
 */
@Data
public class MemberInfoExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("昵称")
    private String nickName;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("电话")
    private String phone;

    @ExcelProperty("会员等级")
    private String levelName;

    @ExcelProperty("性别")
    private Integer gender;

    @ExcelProperty("总花费")
    private BigDecimal cost;

    @ExcelProperty("积分")
    private Long points;

    @ExcelProperty("创建时间")
    private Date createTime;

}
