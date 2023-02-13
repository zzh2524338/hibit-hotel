package cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 房型管理创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoomTypeCreateReqVO extends RoomTypeBaseVO {

    @Schema(description = "描述", example = "你猜")
    private String remark;

}
