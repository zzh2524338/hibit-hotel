package cn.iocoder.yudao.module.hotel.controller.admin.roomratetype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 房价类型更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoomRateTypeUpdateReqVO extends RoomRateTypeBaseVO {

    @Schema(description = " 编号", required = true, example = "31811")
    @NotNull(message = " 编号不能为空")
    private Long id;

}
