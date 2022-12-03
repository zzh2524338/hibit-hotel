package cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 客史信息创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GuestInfoCreateReqVO extends GuestInfoBaseVO {

    @ApiModelProperty(value = "身份证颁证机构", required = true)
    @NotNull(message = "身份证颁证机构不能为空")
    private String authOrganization;

}
