package cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Schema(description = "管理后台 - 会员等级 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberLevelRespVO extends MemberLevelBaseVO {

    @Schema(description = "id", required = true)
    private Long id;

    @Schema(description = "创建时间", required = true)
    private Date createTime;

}
