package cn.iocoder.yudao.module.hotel.controller.admin.company.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 公司信息 Excel 导出 Request VO 参数和 CompanyPageReqVO 是一致的")
@Data
public class CompanyExportReqVO {

    @Schema(description = "公司名称")
    private String name;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "社会统一信用代码")
    private String unifiedSocialCreditCode;

    @Schema(description = "create_time")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
