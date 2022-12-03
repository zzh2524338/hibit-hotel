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
public enum RoomStatusEnum implements IntArrayValuable {

    AVAILABLE(1,"空净"),
    ORDERED(2,"预定"),
    CHECK_IN(3,"入住"),
    DIRTY(4,"空脏"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(RoomStatusEnum::getValue).toArray();

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
