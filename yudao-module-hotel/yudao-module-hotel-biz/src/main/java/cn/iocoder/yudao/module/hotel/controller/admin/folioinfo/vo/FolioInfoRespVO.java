package cn.iocoder.yudao.module.hotel.controller.admin.folioinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 房单信息 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FolioInfoRespVO extends FolioInfoBaseVO {

    @Schema(description = "id", required = true, example = "316")
    private Long id;

    @Schema(description = "订单号", required = true, example = "26788")
    private Long orderId;

    @Schema(description = "房单状态", required = true, example = "1")
    private Short status;

}
