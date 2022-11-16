package cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo;

import lombok.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 房间信息 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class RoomInfoBaseVO {

    @ApiModelProperty(value = "房间号", required = true)
    @NotNull(message = "房间号不能为空")
    private String no;

    @ApiModelProperty(value = "房间类型表", required = true)
    @NotNull(message = "房间类型表不能为空")
    private Integer roomType;

    @ApiModelProperty(value = "楼层", required = true)
    @NotNull(message = "楼层不能为空")
    private Integer floor;

    @ApiModelProperty(value = "状态", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;

    @ApiModelProperty(value = "房间钥匙信息")
    private String keyInfo;

}
