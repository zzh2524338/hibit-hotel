package cn.iocoder.yudao.module.hotel.dal.dataobject.roomtyperate;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 房型价格 DO
 *
 * @author 芋道源码
 */
@TableName("hotel_room_type_rate")
@KeySequence("hotel_room_type_rate_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomTypeRateDO extends BaseDO {

    /**
     *  编号
     */
    @TableId
    private Long id;
    /**
     * 房型编号
     */
    private Long roomTypeId;
    /**
     * 房价类型编号
     */
    private Long roomRateTypeId;
    /**
     * 现在折扣价格
     */
    private BigDecimal roomRate;
    /**
     * 生效日期
     */
    private LocalDateTime accDate;

}
