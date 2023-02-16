package cn.iocoder.yudao.module.hotel.dal.dataobject.memberinfo;

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

import java.math.BigDecimal;

/**
 * 会员信息 DO
 *
 * @author hibit
 */
@TableName("hotel_member_info")
@KeySequence("hotel_member_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 姓名
     */
    private String name;
    /**
     * 证件类型外键
     */
    private Long cardTypeId;

    /**
     * 证件编号
     */
    private String cardNo;
    /**
     * 电话
     */
    private String phone;
    /**
     * 会员等级表主键
     */
    private Long levelId;

    /**
     * 性别
     */
    private Integer gender;
    /**
     * 总花费
     */
    private BigDecimal cost;
    /**
     * 积分
     */
    private Integer points;

    /**
     * 经验值
     */
    private Integer exp;

}
