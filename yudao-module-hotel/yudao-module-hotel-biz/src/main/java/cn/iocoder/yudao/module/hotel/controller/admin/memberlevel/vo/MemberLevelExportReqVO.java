package cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 会员等级 Excel 导出 Request VO", description = "参数和 MemberLevelPageReqVO 是一致的")
@Data
public class MemberLevelExportReqVO {

    @ApiModelProperty(value = "等级名称")
    private String name;

    @ApiModelProperty(value = "会员等级")
    private Integer level;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
