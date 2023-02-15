package cn.iocoder.yudao.module.hotel.controller.admin.building.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 公司分部 Excel 导出 Request VO，参数和 BuildingPageReqVO 是一致的")
@Data
public class BuildingExportReqVO {

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
