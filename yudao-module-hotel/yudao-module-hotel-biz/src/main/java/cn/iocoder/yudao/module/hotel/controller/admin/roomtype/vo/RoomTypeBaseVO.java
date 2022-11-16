package cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo;

import lombok.*;

import java.sql.Blob;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 房间类型 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class RoomTypeBaseVO {

    @ApiModelProperty(value = "面积", required = true)
    @NotNull(message = "面积不能为空")
    private BigDecimal area;

    @ApiModelProperty(value = "最大入住人数", required = true)
    @NotNull(message = "最大入住人数不能为空")
    private Integer livingPopulation;

    @ApiModelProperty(value = "name", required = true)
    @NotNull(message = "name不能为空")
    private String name;

    @ApiModelProperty(value = "价格", required = true)
    @NotNull(message = "价格不能为空")
    private BigDecimal price;

    @ApiModelProperty(value = "描述")
    private String desc;

    @ApiModelProperty(value = "图片")
    private String imgs;

    @ApiModelProperty(value = "房间总量", required = true)
    @NotNull(message = "房间总量不能为空")
    private Integer totalRoom;

}
