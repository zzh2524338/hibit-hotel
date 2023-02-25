package cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 会员信息")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberInfoVO extends MemberInfoBaseVO {
    @Schema(description = "会员等级名称")
    private String levelName;

}
