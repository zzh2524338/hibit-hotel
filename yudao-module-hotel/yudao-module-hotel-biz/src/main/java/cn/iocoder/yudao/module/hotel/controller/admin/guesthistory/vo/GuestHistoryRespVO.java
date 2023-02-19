package cn.iocoder.yudao.module.hotel.controller.admin.guesthistory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 客史信息 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GuestHistoryRespVO extends GuestHistoryBaseVO {

    @Schema(description = "id", required = true, example = "22459")
    private Long id;

    @Schema(description = "生日")
    private LocalDate birthday;

}
