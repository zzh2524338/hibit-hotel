package cn.iocoder.yudao.module.hotel.controller.admin.roomratetype.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 房价类型创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoomRateTypeCreateReqVO extends RoomRateTypeBaseVO {

}
