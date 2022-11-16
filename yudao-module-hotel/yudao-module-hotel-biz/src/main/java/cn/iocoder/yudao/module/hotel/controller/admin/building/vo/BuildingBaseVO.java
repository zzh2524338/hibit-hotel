package cn.iocoder.yudao.module.hotel.controller.admin.building.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 公司分部 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class BuildingBaseVO {

    @ApiModelProperty(value = "name", required = true)
    @NotNull(message = "name不能为空")
    private String name;

    @ApiModelProperty(value = "负责人", required = true)
    @NotNull(message = "负责人不能为空")
    private String manager;

    @ApiModelProperty(value = "电话", required = true)
    @NotNull(message = "电话不能为空")
    private String phone;

    @ApiModelProperty(value = "总楼层", required = true)
    @NotNull(message = "总楼层不能为空")
    private Integer floorNum;

    @ApiModelProperty(value = "地址", required = true)
    @NotNull(message = "地址不能为空")
    private String address;

    @ApiModelProperty(value = "所属公司", required = true)
    @NotNull(message = "所属公司不能为空")
    private Long companyId;

}
