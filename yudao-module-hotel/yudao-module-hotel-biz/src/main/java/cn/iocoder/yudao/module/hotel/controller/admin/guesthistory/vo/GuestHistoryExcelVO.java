package cn.iocoder.yudao.module.hotel.controller.admin.guesthistory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 客史信息 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class GuestHistoryExcelVO {

    @ExcelProperty("ke")
    private Long id;

    @ExcelProperty("客人姓名")
    private String guestName;

    @ExcelProperty("生日")
    private LocalDate birthday;

    @ExcelProperty("电话")
    private String phone;

    @ExcelProperty("性别")
    private Integer gender;

    @ExcelProperty("证件编号")
    private String cardNo;

    @ExcelProperty("地址")
    private String address;

    @ExcelProperty("会员编号")
    private Long memberId;

    @ExcelProperty("最近一次入住")
    private LocalDateTime latestCheckin;

    @ExcelProperty("入住次数")
    private Integer checkinNum;

}
