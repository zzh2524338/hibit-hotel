package cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 房间信息 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class RoomInfoBaseVO {

    @Schema(description = "房间号", required = true)
    @NotNull(message = "房间号不能为空")
    private String no;

    @Schema(description = "房间类型表", required = true)
    @NotNull(message = "房间类型表不能为空")
    private Integer roomType;

    @Schema(description = "楼层", required = true)
    @NotNull(message = "楼层不能为空")
    private Integer floor;

    @Schema(description = "状态", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "房间钥匙信息")
    private String keyInfo;

}
