package cn.iocoder.yudao.module.hotel.controller.admin.company.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 公司信息 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class CompanyExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("公司名称")
    private String name;

    @ExcelProperty("联系电话")
    private String phone;

    @ExcelProperty("地址")
    private String address;

    @ExcelProperty("社会统一信用代码")
    private String unifiedSocialCreditCode;

    @ExcelProperty("create_time")
    private Date createTime;

}
