package cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 房型管理更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoomTypeUpdateReqVO extends RoomTypeBaseVO {

    @Schema(description = "id 主键", required = true, example = "24472")
    @NotNull(message = "id 主键不能为空")
    private Long id;

    @Schema(description = "描述", example = "你猜")
    private String remark;

}
