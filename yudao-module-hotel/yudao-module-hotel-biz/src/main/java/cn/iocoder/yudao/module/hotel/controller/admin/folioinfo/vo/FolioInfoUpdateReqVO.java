package cn.iocoder.yudao.module.hotel.controller.admin.folioinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 房单信息更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FolioInfoUpdateReqVO extends FolioInfoBaseVO {

    @Schema(description = "id", required = true, example = "316")
    @NotNull(message = "id不能为空")
    private Long id;

    @Schema(description = "订单号", required = true, example = "26788")
    @NotNull(message = "订单号不能为空")
    private Long orderId;

    @Schema(description = "房单状态", required = true, example = "1")
    @NotNull(message = "房单状态不能为空")
    private Short status;

}
