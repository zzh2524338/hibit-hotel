package cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo;

import lombok.*;

import java.time.LocalDateTime;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 客史信息 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GuestInfoRespVO extends GuestInfoBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "最近一次入住", required = true)
    private LocalDateTime latestCheckin;

    @ApiModelProperty(value = "总入住天数", required = true)
    private Integer stayNightNum;

    @ApiModelProperty(value = "入住次数", required = true)
    private Integer checkinNum;

    @ApiModelProperty(value = "创建时间", required = true)
    private LocalDateTime createTime;

}
