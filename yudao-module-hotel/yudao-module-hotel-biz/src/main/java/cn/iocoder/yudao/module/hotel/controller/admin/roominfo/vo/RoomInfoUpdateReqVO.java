package cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 房间信息更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoomInfoUpdateReqVO extends RoomInfoBaseVO {

    @Schema(description = "id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

    @Schema(description = "备注")
    private String remark;

}
