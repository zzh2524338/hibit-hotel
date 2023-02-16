package cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 会员信息 Excel 导出 Request VO 参数和 MemberInfoPageReqVO 是一致的")
@Data
public class MemberInfoExportReqVO {

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "会员等级")
    private String levelId;

    @Schema(description = "总花费")
    private BigDecimal cost;

}
