package cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 会员等级 Excel 导出 Request VO 参数和 MemberLevelPageReqVO 是一致的")
@Data
public class MemberLevelExportReqVO {

    @Schema(description = "等级名称")
    private String name;

    @Schema(description = "会员等级")
    private Integer level;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
