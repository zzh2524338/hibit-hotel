package cn.iocoder.yudao.module.hotel.dal.dataobject.roomtype;

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

/**
 * 房型管理 DO
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
     * id 主键
     */
    @TableId
    private Long id;
    /**
     * uuid
     */
    private String uuid;
    /**
     * 房型名称
     */
    private String name;
    /**
     * 房型编号
     */
    private String code;
    /**
     * 大床数量
     */
    private Integer bedCount;
    /**
     * 入住人数
     */
    private Integer maxCheckInCount;
    /**
     * 编号，目前与id同号
     */
    private Long masterRoomTypeId;
    /**
     * 描述
     */
    private String remark;
    /**
     * 备注
     */
    private String description;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 面积
     */
    private BigDecimal area;
    /**
     * ota 房间数
     */
    private Integer otaRoomCount;
    /**
     * ota 状态
     */
    private Integer otaState;
    /**
     * 控房数
     */
    private Integer roomControl;

}
