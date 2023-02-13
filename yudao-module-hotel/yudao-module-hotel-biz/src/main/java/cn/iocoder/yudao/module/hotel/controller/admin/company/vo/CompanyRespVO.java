package cn.iocoder.yudao.module.hotel.controller.admin.company.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Schema(description = "管理后台 - 公司信息 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CompanyRespVO extends CompanyBaseVO {

    @Schema(description = "id", required = true)
    private Long id;

    @Schema(description = "create_time", required = true)
    private Date createTime;

}
