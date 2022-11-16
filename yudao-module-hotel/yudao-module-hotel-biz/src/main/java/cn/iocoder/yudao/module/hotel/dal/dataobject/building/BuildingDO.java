package cn.iocoder.yudao.module.hotel.dal.dataobject.building;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 公司分部 DO
 *
 * @author 芋道源码
 */
@TableName("hotel_building")
@KeySequence("hotel_building_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuildingDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * name
     */
    private String name;
    /**
     * 负责人
     */
    private String manager;
    /**
     * 电话
     */
    private String phone;
    /**
     * 总楼层
     */
    private Integer floorNum;
    /**
     * 地址
     */
    private String address;
    /**
     * 所属公司
     */
    private Long companyId;

}
