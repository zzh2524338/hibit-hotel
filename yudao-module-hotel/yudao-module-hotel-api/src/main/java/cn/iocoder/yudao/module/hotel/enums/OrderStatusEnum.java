package cn.iocoder.yudao.module.hotel.enums;


import cn.iocoder.yudao.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum implements IntArrayValuable {

    ORDERED(1, "预定"),
    TODAY_ARRIVAL(2, "今日抵店"),

    CHECK_IN(3, "已入住"),
    CHECK_OUT(4, "已退房"),

    TIME_OUT(5, "超时未处理"),
    CANCEL(6, "已取消"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(OrderStatusEnum::getValue).toArray();

    /**
     * 状态
     */
    private final Integer value;
    /**
     * 状态名
     */
    private final String name;

    @Override
    public int[] array() {
        return ARRAYS;
    }
}
