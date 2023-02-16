package cn.iocoder.yudao.module.hotel.controller.admin.roomratetype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 房价类型 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoomRateTypeRespVO extends RoomRateTypeBaseVO {

    @Schema(description = " 编号", required = true, example = "31811")
    private Long id;

    @Schema(description = "创建时间", required = true)
    private LocalDateTime createTime;

}
