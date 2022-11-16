package cn.iocoder.yudao.module.hotel.controller.admin.company.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 公司信息 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CompanyRespVO extends CompanyBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "create_time", required = true)
    private Date createTime;

}
