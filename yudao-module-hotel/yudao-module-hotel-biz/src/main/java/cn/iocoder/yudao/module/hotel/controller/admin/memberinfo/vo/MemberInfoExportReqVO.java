package cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@ApiModel(value = "管理后台 - 会员信息 Excel 导出 Request VO", description = "参数和 MemberInfoPageReqVO 是一致的")
@Data
public class MemberInfoExportReqVO {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "会员等级")
    private String levelId;

    @ApiModelProperty(value = "总花费")
    private BigDecimal cost;

}
