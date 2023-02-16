package cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 会员信息 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MemberInfoBaseVO {

    @Schema(description = "昵称", required = true)
    // @NotNull(message = "昵称不能为空")
    private String nickName;

    @Schema(description = "姓名", required = true)
    @NotNull(message = "姓名不能为空")
    private String name;

    @Schema(description = "电话", required = true)
    @NotNull(message = "电话不能为空")
    private String phone;

    @Schema(description = "会员等级", required = true)
    @NotNull(message = "会员等级不能为空")
    private Long levelId;

    @Schema(description = "性别", required = true)
    // @NotNull(message = "性别不能为空")
    private Integer gender;

    @Schema(description = "总花费", required = true)
    @NotNull(message = "总花费不能为空")
    private BigDecimal cost;

    @Schema(description = "积分", required = true)
    @NotNull(message = "积分不能为空")
    private Long points;

    @Schema(description = "证件号", required = true)
    // @NotBlank(message = "证件号不能为空")
    private String cardNo;

    @Schema(description = "证件类型", required = true)
    // @NotNull(message = "证件类型不能为空")
    private Integer cardTypeId;

}
