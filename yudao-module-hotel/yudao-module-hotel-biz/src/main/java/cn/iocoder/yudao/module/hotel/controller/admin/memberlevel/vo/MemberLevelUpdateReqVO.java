package cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 会员等级更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberLevelUpdateReqVO extends MemberLevelBaseVO {

    @Schema(description = "id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

}
