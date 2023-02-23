package cn.iocoder.yudao.module.hotel.dal.dataobject.orderinfo;

import lombok.*;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

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
     * 订单状态
     */
    private Integer status;
    /**
     * 预定人名称
     */
    private String bookingPerson;
    /**
     * 联系人
     */
    private String contactName;
    /**
     * 联系电话
     */
    private String contactNumber;
    /**
     * 中介号
     */
    private String agencyCode;
    /**
     * 客人信息
     */
    private String bookingGuestInfo;
    /**
     * 房单
     */
    private Long folioId;
    /**
     * 房单类型
     */
    private Integer folioType;
    /**
     * 订单来源
     */
    private Integer sourceId;
    /**
     * 订单来源(小类)
     */
    private Integer subSourceId;
    /**
     * 是否担保
     */
    private Boolean assure;
    /**
     * 担保时间
     */
    private LocalDateTime assureTime;
    /**
     * 活动id(暂时没用)
     */
    private Long marketId;
    /**
     * 房价类型
     */
    private Long roomRateTypeId;
    /**
     * 入住时间
     */
    private LocalDateTime arrivalTime;
    /**
     * 离店时间
     */
    private LocalDateTime departTime;
    /**
     * 客源
     */
    private Integer guestsSourceId;
    /**
     * 客源(小类)
     */
    private Integer guestsSubSourceId;
    /**
     * 是否爆单预定
     */
    private Boolean ignoreRoomStatus;
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
    private Short orderStatus;
    /**
     * 房间信息
     */
    private String roomInfo;
    /**
     * 会员信息快照
     */
    private String memberInfo;

}
