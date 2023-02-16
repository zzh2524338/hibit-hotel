package cn.iocoder.yudao.module.hotel.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Schema(description = "管理后台 - 订单创建 Request VO")
@Data
@ToString(callSuper = true)
public class GuestInfoVO {

    @Schema(description = "姓名", required = true)
    @NotBlank(message = "姓名不能为空")
    private String name;

    @Schema(description = "身份证号", required = true)
    @NotBlank(message = "身份证号码不能为空")
    private String certNo;

    @Schema(description = "地址", required = true)
    @NotBlank(message = "地址不能为空")
    private String address;

    @Schema(description = "颁证机构", required = true)
    @NotBlank(message = "颁证机构不能为空")
    private String issuingAuthority;
}
