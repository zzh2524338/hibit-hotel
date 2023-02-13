package cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
* 房型管理 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class RoomTypeBaseVO {

    @Schema(description = "房型名称", required = true, example = "王五")
    @NotNull(message = "房型名称不能为空")
    private String name;

    @Schema(description = "房型编号")
    private String code;

    @Schema(description = "大床数量", required = true, example = "2076")
    @NotNull(message = "大床数量不能为空")
    private Integer bedCount;

    @Schema(description = "入住人数", required = true, example = "9751")
    @NotNull(message = "入住人数不能为空")
    private Integer maxCheckInCount;

    @Schema(description = "备注", example = "你猜")
    private String description;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "面积", required = true)
    @NotNull(message = "面积不能为空")
    private BigDecimal area;

    @Schema(description = "控房数", required = true)
    @NotNull(message = "控房数不能为空")
    private Integer roomControl;

}
