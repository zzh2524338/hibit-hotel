package cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 客史信息 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GuestInfoRespVO extends GuestInfoBaseVO {

    @Schema(description = "id", required = true)
    private Long id;

    @Schema(description = "最近一次入住", required = true)
    private LocalDateTime latestCheckin;

    @Schema(description = "总入住天数", required = true)
    private Integer stayNightNum;

    @Schema(description = "入住次数", required = true)
    private Integer checkinNum;

    @Schema(description = "创建时间", required = true)
    private LocalDateTime createTime;

}
