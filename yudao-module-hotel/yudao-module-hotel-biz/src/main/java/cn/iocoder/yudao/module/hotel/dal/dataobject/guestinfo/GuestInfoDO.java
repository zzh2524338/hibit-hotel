package cn.iocoder.yudao.module.hotel.dal.dataobject.guestinfo;

import lombok.*;

import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客史信息 DO
 *
 * @author 芋道源码
 */
@TableName("hotel_guest_info")
@KeySequence("hotel_guest_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestInfoDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 姓名
     */
    private String name;
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
     * 身份证
     */
    private String idCard;
    /**
     * 身份证地址
     */
    private String address;
    /**
     * 身份证颁证机构
     */
    private String authOrganization;
    /**
     * 会员ID
     */
    private Integer memberId;
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

}
