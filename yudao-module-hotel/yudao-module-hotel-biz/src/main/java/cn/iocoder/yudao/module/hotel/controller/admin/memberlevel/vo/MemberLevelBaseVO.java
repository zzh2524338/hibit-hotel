package cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 会员等级 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MemberLevelBaseVO {

    @Schema(description = "等级名称", required = true)
    @NotNull(message = "等级名称不能为空")
    private String name;

    @Schema(description = "会员等级", required = true)
    @NotNull(message = "会员等级不能为空")
    private Integer level;

    @Schema(description = "等级开始值", required = true)
    @NotNull(message = "等级开始值不能为空")
    private Integer startPoints;

    @Schema(description = "等级结束值", required = true)
    @NotNull(message = "等级结束值不能为空")
    private Integer endPoints;

    @Schema(description = "折扣", required = true)
    @NotNull(message = "折扣不能为空")
    private Integer discount;

}
