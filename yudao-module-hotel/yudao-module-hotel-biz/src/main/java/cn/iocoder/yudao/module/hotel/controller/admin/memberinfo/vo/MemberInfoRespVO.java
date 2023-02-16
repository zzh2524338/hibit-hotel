package cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Schema(description = "管理后台 - 会员信息 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberInfoRespVO extends MemberInfoBaseVO {

    @Schema(description = "id", required = true)
    private Long id;

    @Schema(description = "创建时间", required = true)
    private Date createTime;

    @Schema(description = "会员等级名称", required = true)
    private String levelName;

    @Schema(description = "总积分", required = true)
    private Integer exp;
}
