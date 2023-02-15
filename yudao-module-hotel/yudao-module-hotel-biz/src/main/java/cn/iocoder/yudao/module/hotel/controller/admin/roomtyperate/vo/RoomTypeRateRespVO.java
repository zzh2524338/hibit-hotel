package cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 房型价格 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoomTypeRateRespVO extends RoomTypeRateBaseVO {

    @Schema(description = " 编号", required = true, example = "7217")
    private Long id;

    @Schema(description = "创建时间", required = true)
    private LocalDateTime createTime;

}
