package cn.iocoder.yudao.module.hotel.dal.dataobject.guesthistory;

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
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 客史信息 DO
 *
 * @author 芋道源码
 */
@TableName("hotel_guest_history")
@KeySequence("hotel_guest_history_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestHistoryDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 客人姓名
     */
    private String guestName;
    /**
     * 生日
     */
    private LocalDate birthday;
    /**
     * 电话
     */
    private String phone;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 证件编号
     */
    private String cardNo;
    /**
     * 证件类型
     */
    private Integer cardType;
    /**
     * 地址
     */
    private String address;
    /**
     * 会员编号
     */
    private Long memberId;
    /**
     * 最近一次入住
     */
    private LocalDateTime latestCheckin;
    /**
     * 总入住天数
     */
    private Integer stayNightNum;
    /**
     * 入住次数
     */
    private Integer checkinNum;

    /**
     * 邮箱
     */
    private String email;

}
