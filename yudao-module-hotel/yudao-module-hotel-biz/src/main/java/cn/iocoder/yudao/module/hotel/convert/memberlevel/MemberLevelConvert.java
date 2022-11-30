package cn.iocoder.yudao.module.hotel.convert.memberlevel;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberlevel.MemberLevelDO;

/**
 * 会员等级 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface MemberLevelConvert {

    MemberLevelConvert INSTANCE = Mappers.getMapper(MemberLevelConvert.class);

    MemberLevelDO convert(MemberLevelCreateReqVO bean);

    MemberLevelDO convert(MemberLevelUpdateReqVO bean);

    MemberLevelRespVO convert(MemberLevelDO bean);

    List<MemberLevelRespVO> convertList(List<MemberLevelDO> list);

    PageResult<MemberLevelRespVO> convertPage(PageResult<MemberLevelDO> page);

    List<MemberLevelExcelVO> convertList02(List<MemberLevelDO> list);

}
