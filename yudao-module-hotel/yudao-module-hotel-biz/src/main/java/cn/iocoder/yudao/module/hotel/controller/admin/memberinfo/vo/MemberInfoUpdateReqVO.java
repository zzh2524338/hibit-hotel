package cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 会员信息更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberInfoUpdateReqVO extends MemberInfoBaseVO {

    @Schema(description = "id", required = true)
    @NotNull(message = "id 不能为空")
    private Long id;
}
