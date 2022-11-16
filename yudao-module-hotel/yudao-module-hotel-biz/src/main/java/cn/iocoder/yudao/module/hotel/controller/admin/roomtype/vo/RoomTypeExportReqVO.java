package cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 房间类型 Excel 导出 Request VO", description = "参数和 RoomTypePageReqVO 是一致的")
@Data
public class RoomTypeExportReqVO {

    @ApiModelProperty(value = "最大入住人数")
    private Integer livingPopulation;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "房间总量")
    private Integer totalRoom;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
