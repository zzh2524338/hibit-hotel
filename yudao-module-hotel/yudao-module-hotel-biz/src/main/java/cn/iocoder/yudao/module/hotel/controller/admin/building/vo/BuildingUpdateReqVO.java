package cn.iocoder.yudao.module.hotel.controller.admin.building.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 公司分部更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BuildingUpdateReqVO extends BuildingBaseVO {

    @Schema(description = "id", required = true, example = "28296")
    @NotNull(message = "id不能为空")
    private Long id;

}
