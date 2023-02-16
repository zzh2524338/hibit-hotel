package cn.iocoder.yudao.module.hotel.dal.dataobject.order;

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
import java.util.Date;

/**
 * 订单 DO
 *
 * @author 芋道源码
 */
@TableName("hotel_order_info")
@KeySequence("hotel_order_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 订单号
     */
    private String uuid;
    /**
     * 预定人名称
     */
    private String orderGuestName;
    /**
     * 联系人
     */
    private String contactPerson;
    /**
     * 联系电话
     */
    private String contactNumber;
    /**
     * 中介号
     */
    private String intermediaryNumber;
    /**
     * 房间信息
     */
    private String roomInfo;
    /**
     * 会员信息快照
     */
    private String memberInfo;
    /**
     * 订单来源
     */
    private Long guestSourceId;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 原价
     */
    private BigDecimal originalPrice;
    /**
     * 折后实际价格
     */
    private BigDecimal discountPrice;
    /**
     * 实际付款
     */
    private BigDecimal actuallyPaid;
    /**
     * 订单状态
     */
    private Integer orderStatus;
    /**
     * 入住时间
     */
    private Date checkInTime;
    /**
     * 客人信息
     */
    private String guestInfo;
    /**
     * 离店时间
     */
    private Date checkOutTime;

}
