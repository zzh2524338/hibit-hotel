package cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 会员信息更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberInfoUpdateReqVO extends MemberInfoBaseVO {
    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "id 不能为空")
    private Long id;
}
