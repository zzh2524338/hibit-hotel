package cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 客史信息更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GuestInfoUpdateReqVO extends GuestInfoBaseVO {

    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "身份证颁证机构", required = true)
    @NotNull(message = "身份证颁证机构不能为空")
    private String authOrganization;

}
