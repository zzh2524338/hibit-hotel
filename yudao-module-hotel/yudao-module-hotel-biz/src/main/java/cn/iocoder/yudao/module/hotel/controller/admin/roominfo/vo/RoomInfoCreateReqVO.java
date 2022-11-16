package cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 房间信息创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoomInfoCreateReqVO extends RoomInfoBaseVO {

    @ApiModelProperty(value = "备注")
    private String remark;

}
