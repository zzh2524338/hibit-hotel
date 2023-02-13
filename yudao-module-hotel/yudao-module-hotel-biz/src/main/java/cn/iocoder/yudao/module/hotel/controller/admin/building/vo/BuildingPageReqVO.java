package cn.iocoder.yudao.module.hotel.controller.admin.building.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 公司分部分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BuildingPageReqVO extends PageParam {

    @Schema(description = "name", example = "王五")
    private String name;

    @Schema(description = "负责人")
    private String manager;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "所属公司", example = "24139")
    private Long companyId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
