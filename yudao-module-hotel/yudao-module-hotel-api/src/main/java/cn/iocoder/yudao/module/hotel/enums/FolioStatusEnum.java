package cn.iocoder.yudao.module.hotel.enums;


import cn.iocoder.yudao.framework.common.core.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 房单状态
 * @author liurongtong
 */
@Getter
@AllArgsConstructor
public enum FolioStatusEnum implements IntArrayValuable {
    UNASSIGNED(1,"未排房"),
    ASSIGNED(2,"已排房"),
    CHECK_IN(3,"入住"),
    COMPLETED(4,"已完成"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(FolioStatusEnum::getValue).toArray();

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
