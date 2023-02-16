package cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 客史信息创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GuestInfoCreateReqVO extends GuestInfoBaseVO {

    @Schema(description = "身份证颁证机构", required = false)
    // @NotNull(message = "身份证颁证机构不能为空")
    private String authOrganization;

}
