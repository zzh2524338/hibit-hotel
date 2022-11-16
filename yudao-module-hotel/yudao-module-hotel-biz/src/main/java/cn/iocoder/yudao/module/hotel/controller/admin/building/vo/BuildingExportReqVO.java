package cn.iocoder.yudao.module.hotel.controller.admin.building.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@ApiModel(value = "管理后台 - 公司分部 Excel 导出 Request VO", description = "参数和 BuildingPageReqVO 是一致的")
@Data
public class BuildingExportReqVO {

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "负责人")
    private String manager;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "总楼层")
    private Integer floorNum;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "所属公司")
    private Long companyId;

}
