package cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo;

import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 客史信息 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class GuestInfoExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("生日")
    private LocalDate birthday;

    @ExcelProperty("电话")
    private String phone;

    @ExcelProperty(value = "性别", converter = DictConvert.class)
    @DictFormat("system_user_sex") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer gender;

    @ExcelProperty("身份证")
    private String idCard;

    @ExcelProperty("身份证地址")
    private String address;

    @ExcelProperty("会员ID")
    private Integer memberId;

    @ExcelProperty("最近一次入住")
    private LocalDateTime latestCheckin;

    @ExcelProperty("总入住天数")
    private Integer stayNightNum;

    @ExcelProperty("入住次数")
    private Integer checkinNum;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
