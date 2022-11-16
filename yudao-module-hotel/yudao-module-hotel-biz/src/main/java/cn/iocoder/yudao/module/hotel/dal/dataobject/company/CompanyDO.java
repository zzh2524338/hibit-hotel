package cn.iocoder.yudao.module.hotel.dal.dataobject.company;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 公司信息 DO
 *
 * @author 芋道源码
 */
@TableName("hotel_company")
@KeySequence("hotel_company_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 公司名称
     */
    private String name;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 地址
     */
    private String address;
    /**
     * 社会统一信用代码
     */
    private String unifiedSocialCreditCode;

}
