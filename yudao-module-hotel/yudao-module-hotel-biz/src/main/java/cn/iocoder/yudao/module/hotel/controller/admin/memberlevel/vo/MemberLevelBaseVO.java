package cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 会员等级 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class MemberLevelBaseVO {

    @ApiModelProperty(value = "等级名称", required = true)
    @NotNull(message = "等级名称不能为空")
    private String name;

    @ApiModelProperty(value = "会员等级", required = true)
    @NotNull(message = "会员等级不能为空")
    private Integer level;

    @ApiModelProperty(value = "等级开始值", required = true)
    @NotNull(message = "等级开始值不能为空")
    private Integer startPoints;

    @ApiModelProperty(value = "等级结束值", required = true)
    @NotNull(message = "等级结束值不能为空")
    private Integer endPoints;

    @ApiModelProperty(value = "折扣", required = true)
    @NotNull(message = "折扣不能为空")
    private Integer discount;

}
