package cn.iocoder.yudao.module.hotel.dal.dataobject.folioinfo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 房单信息 DO
 *
 * @author 芋道源码
 */
@TableName("hotel_folio_info")
@KeySequence("hotel_folio_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FolioInfoDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 订单号
     */
    private Long orderId;
    /**
     * 房间类型
     */
    private Long roomTypeId;
    /**
     * 房间信息
     */
    private Long roomId;
    /**
     * 房单状态
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 客人信息
     */
    private String guestInfo;
    /**
     * 到店时间
     */
    private LocalDateTime arrivalTime;
    /**
     * 离店时间
     */
    private LocalDateTime departTime;
    /**
     * 订单编号，仅当房间有人入住才有值
     */
    private String orderUuid;

}
