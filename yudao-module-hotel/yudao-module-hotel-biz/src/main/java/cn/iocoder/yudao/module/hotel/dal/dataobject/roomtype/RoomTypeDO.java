package cn.iocoder.yudao.module.hotel.dal.dataobject.roomtype;

import lombok.*;

import java.sql.Blob;
import java.util.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

import static org.apache.ibatis.type.JdbcType.BLOB;

/**
 * 房间类型 DO
 *
 * @author 芋道源码
 */
@TableName("hotel_room_type")
@KeySequence("hotel_room_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomTypeDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 面积
     */
    private BigDecimal area;
    /**
     * 最大入住人数
     */
    private Integer livingPopulation;
    /**
     * name
     */
    private String name;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 描述
     */
    @TableField(jdbcType = BLOB, value = "`desc`")
    private String desc;
    /**
     * 图片
     */
    private String imgs;
    /**
     * 房间总量
     */
    private Integer totalRoom;

}
