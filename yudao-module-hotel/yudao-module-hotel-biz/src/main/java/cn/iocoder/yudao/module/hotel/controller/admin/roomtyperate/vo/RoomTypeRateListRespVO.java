package cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo;

import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtyperate.RoomTypeRateDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Schema(description = "管理后台 - 房型价格分页 Request VO")
@Data
@ToString(callSuper = true)
public class RoomTypeRateListRespVO {

    @Schema(description = "生效时间", example = "2022-11-01 22:00:00")
    private String accDate;

    @Schema(description = "房价类型编号", example = "4867")
    private Long roomRateTypeId;

    @Schema(description = "房型各个会员价格", example = "4867")
    private List<RoomTypeAndRate> roomTypeAndRates;


    @Data
    @ToString(callSuper = true)
    public static class RoomTypeAndRate {

        @Schema(description = "房间类型编号", example = "4867")
        private Long roomTypeId;

        private List<RoomTypeRateDO> roomTypeRateDOList;
    }
}
