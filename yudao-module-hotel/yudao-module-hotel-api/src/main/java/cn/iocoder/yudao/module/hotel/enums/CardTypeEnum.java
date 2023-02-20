package cn.iocoder.yudao.module.hotel.enums;

import cn.iocoder.yudao.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 房间状态
 * @author liurongtong
 */
@Getter
@AllArgsConstructor
public enum CardTypeEnum implements IntArrayValuable {
    ID_CARD(1,"身份证"),
    DRIVER_LICENSE(2,"驾驶证"),
    ;
    public static final int[] ARRAYS = Arrays.stream(values())
            .mapToInt(CardTypeEnum::getValue)
            .toArray();

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
