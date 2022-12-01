package cn.iocoder.yudao.module.hotel.controller.admin.roominfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 房间信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoomInfoPageReqVO extends PageParam {

    @ApiModelProperty(value = "房间号")
    private String no;

    @ApiModelProperty(value = "房间类型")
    private Integer roomType;

    @ApiModelProperty(value = "楼层")
    private Integer floor;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "房间钥匙信息")
    private String keyInfo;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
