package cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 客史信息更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GuestInfoUpdateReqVO extends GuestInfoBaseVO {

    @Schema(description = "id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

    @Schema(description = "身份证颁证机构", required = true)
    @NotNull(message = "身份证颁证机构不能为空")
    private String authOrganization;

}
