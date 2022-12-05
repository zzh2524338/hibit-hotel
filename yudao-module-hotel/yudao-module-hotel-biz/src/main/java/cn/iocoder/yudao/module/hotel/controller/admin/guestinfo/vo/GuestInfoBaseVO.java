package cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 客史信息 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class GuestInfoBaseVO {

    @ApiModelProperty(value = "姓名", required = true)
    @NotNull(message = "姓名不能为空")
    private String name;

    @ApiModelProperty(value = "生日", required = true)
    // @NotNull(message = "生日不能为空")
    private LocalDate birthday;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "性别", required = true)
    // @NotNull(message = "性别不能为空")
    private Integer gender;

    @ApiModelProperty(value = "身份证", required = true)
    @NotNull(message = "身份证不能为空")
    private String idCard;

    @ApiModelProperty(value = "身份证地址", required = true)
    // @NotNull(message = "身份证地址不能为空")
    private String address;

    @ApiModelProperty(value = "会员ID")
    private Integer memberId;

}
