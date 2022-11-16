package cn.iocoder.yudao.module.hotel.controller.admin.company.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 公司信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CompanyPageReqVO extends PageParam {

    @ApiModelProperty(value = "公司名称")
    private String name;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "社会统一信用代码")
    private String unifiedSocialCreditCode;

    @ApiModelProperty(value = "create_time")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
