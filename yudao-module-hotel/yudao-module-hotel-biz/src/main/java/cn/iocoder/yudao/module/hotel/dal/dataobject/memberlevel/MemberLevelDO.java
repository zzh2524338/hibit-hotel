package cn.iocoder.yudao.module.hotel.dal.dataobject.memberlevel;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 会员等级 DO
 *
 * @author 芋道源码
 */
@TableName("hotel_member_level")
@KeySequence("hotel_member_level_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberLevelDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 等级名称
     */
    private String name;
    /**
     * 会员等级
     */
    private Integer level;
    /**
     * 等级开始值
     */
    private Integer startPoints;
    /**
     * 等级结束值
     */
    private Integer endPoints;
    /**
     * 折扣
     */
    private Integer discount;

}
