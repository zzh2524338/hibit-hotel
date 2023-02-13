package cn.iocoder.yudao.module.hotel.controller.admin.building.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 公司分部 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BuildingRespVO extends BuildingBaseVO {

    @Schema(description = "id", required = true, example = "28296")
    private Long id;

    @Schema(description = "创建时间", required = true)
    private LocalDateTime createTime;

}
