package cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo;

import lombok.*;

import java.math.BigInteger;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 会员信息 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class MemberInfoBaseVO {

    @ApiModelProperty(value = "昵称", required = true)
    @NotNull(message = "昵称不能为空")
    private String nickName;

    @ApiModelProperty(value = "姓名", required = true)
    @NotNull(message = "姓名不能为空")
    private String name;

    @ApiModelProperty(value = "电话", required = true)
    @NotNull(message = "电话不能为空")
    private String phone;

    @ApiModelProperty(value = "会员等级", required = true)
    @NotNull(message = "会员等级不能为空")
    private Long levelId;

    @ApiModelProperty(value = "性别", required = true)
    @NotNull(message = "性别不能为空")
    private Integer gender;

    @ApiModelProperty(value = "总花费", required = true)
    @NotNull(message = "总花费不能为空")
    private BigDecimal cost;

    @ApiModelProperty(value = "积分", required = true)
    @NotNull(message = "积分不能为空")
    private Long points;

}
