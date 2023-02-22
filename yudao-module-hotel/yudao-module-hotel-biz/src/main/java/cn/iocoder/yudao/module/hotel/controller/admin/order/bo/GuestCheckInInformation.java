package cn.iocoder.yudao.module.hotel.controller.admin.order.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "酒店管理后台 - 订单创建中的客人信息")
@Data
public class GuestCheckInInformation {
    @Schema(description = "客人编号")
    private String guestId;

    @Schema(description = "客人姓名", required = true)
    @NotNull(message = "客人姓名不能为空")
    private String name;

    @Schema(description = "手机号")
    @Length(max = 13)
    private String mobile;

    @Schema(description = "证件类型")
    @NotNull(message = "证件类型不能为空")
    private Integer cardType;
    @Schema(description = "证件编号")
    @NotBlank(message = "证件编号不能为空")
    @Length(max = 32)
    private String cardNo;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "邮箱")
    private String email;

}
