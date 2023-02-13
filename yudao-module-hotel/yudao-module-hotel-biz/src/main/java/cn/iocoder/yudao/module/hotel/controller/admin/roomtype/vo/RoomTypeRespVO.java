package cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 房型管理 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoomTypeRespVO extends RoomTypeBaseVO {

    @Schema(description = "id 主键", required = true, example = "24472")
    private Long id;

    @Schema(description = "创建时间", required = true)
    private LocalDateTime createTime;

}
