package cn.iocoder.yudao.module.hotel.dal.dataobject.roominfo;

import cn.iocoder.yudao.module.hotel.enums.RoomStatusEnum;
import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 房间信息 DO
 *
 * @author 芋道源码
 */
@TableName("hotel_room_info")
@KeySequence("hotel_room_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomInfoDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 房间号
     */
    private String no;
    /**
     * 房间类型表
     */
    private Integer roomType;
    /**
     * 楼层
     */
    private Integer floor;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 房间钥匙信息
     */
    private String keyInfo;

}
