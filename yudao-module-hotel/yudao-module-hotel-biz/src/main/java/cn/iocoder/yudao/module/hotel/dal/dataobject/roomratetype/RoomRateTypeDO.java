package cn.iocoder.yudao.module.hotel.dal.dataobject.roomratetype;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 房价类型 DO
 *
 * @author 芋道源码
 */
@TableName("hotel_room_rate_type")
@KeySequence("hotel_room_rate_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomRateTypeDO extends BaseDO {

    /**
     *  编号
     */
    @TableId
    private Long id;
    /**
     * uuid
     */
    private String uuid;
    /**
     * 名称
     */
    private String name;

}
