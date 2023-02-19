package cn.iocoder.yudao.module.hotel.controller.admin.guesthistory.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 客史信息创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GuestHistoryCreateReqVO extends GuestHistoryBaseVO {

    @Schema(description = "身份证", example = "2")
    private Long cardType;

}
