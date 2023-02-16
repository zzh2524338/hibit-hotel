package cn.iocoder.yudao.module.hotel.dal.dataobject.roomratetype;

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
     * 编号
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
