package cn.iocoder.yudao.module.hotel.dal.dataobject.memberlevel;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
