package cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 房型价格更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoomTypeRateUpdateReqVO extends RoomTypeRateBaseVO {

    @Schema(description = " 编号", required = true, example = "7217")
    @NotNull(message = " 编号不能为空")
    private Long id;

}
