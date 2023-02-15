package cn.iocoder.yudao.module.hotel.controller.admin.building.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
* 公司分部 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class BuildingBaseVO {

    @Schema(description = "name", required = true, example = "王五")
    @NotNull(message = "name不能为空")
    private String name;

    @Schema(description = "负责人", required = true)
    @NotNull(message = "负责人不能为空")
    private String manager;

    @Schema(description = "电话", required = true)
    @NotNull(message = "电话不能为空")
    private String phone;

    @Schema(description = "总楼层", required = true)
    @NotNull(message = "总楼层不能为空")
    private Integer floorNum;

    @Schema(description = "地址", required = true)
    @NotNull(message = "地址不能为空")
    private String address;

    @Schema(description = "所属公司", required = true, example = "24139")
    @NotNull(message = "所属公司不能为空")
    private Long companyId;

}
