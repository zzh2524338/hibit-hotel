package cn.iocoder.yudao.module.hotel.controller.admin.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ApiModel("管理后台 - 订单创建 Request VO")
@Data
@ToString(callSuper = true)
public class GuestInfoVO {

    @ApiModelProperty(value = "姓名", required = true)
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ApiModelProperty(value = "身份证号", required = true)
    @NotBlank(message = "身份证号码不能为空")
    private String certNo;

    @ApiModelProperty(value = "地址", required = true)
    @NotBlank(message = "地址不能为空")
    private String address;

    @ApiModelProperty(value = "颁证机构", required = true)
    @NotBlank(message = "颁证机构不能为空")
    private String issuingAuthority;
}
