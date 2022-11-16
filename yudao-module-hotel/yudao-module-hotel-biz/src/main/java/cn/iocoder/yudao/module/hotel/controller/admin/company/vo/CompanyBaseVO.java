package cn.iocoder.yudao.module.hotel.controller.admin.company.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 公司信息 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class CompanyBaseVO {

    @ApiModelProperty(value = "公司名称", required = true)
    @NotNull(message = "公司名称不能为空")
    private String name;

    @ApiModelProperty(value = "联系电话", required = true)
    @NotNull(message = "联系电话不能为空")
    private String phone;

    @ApiModelProperty(value = "地址", required = true)
    @NotNull(message = "地址不能为空")
    private String address;

    @ApiModelProperty(value = "社会统一信用代码", required = true)
    @NotNull(message = "社会统一信用代码不能为空")
    private String unifiedSocialCreditCode;

}
