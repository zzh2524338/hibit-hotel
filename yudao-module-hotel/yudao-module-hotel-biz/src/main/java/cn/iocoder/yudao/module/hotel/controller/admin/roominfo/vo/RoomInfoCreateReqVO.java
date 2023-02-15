package cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 房间信息创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoomInfoCreateReqVO extends RoomInfoBaseVO {

    @Schema(description = "备注")
    private String remark;

}
