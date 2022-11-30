package cn.iocoder.yudao.module.hotel.convert.memberinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberinfo.MemberInfoDO;

/**
 * 会员信息 Convert
 *
 * @author hibit
 */
@Mapper
public interface MemberInfoConvert {

    MemberInfoConvert INSTANCE = Mappers.getMapper(MemberInfoConvert.class);

    MemberInfoDO convert(MemberInfoCreateReqVO bean);

    MemberInfoDO convert(MemberInfoUpdateReqVO bean);

    MemberInfoRespVO convert(MemberInfoDO bean);

    List<MemberInfoRespVO> convertList(List<MemberInfoDO> list);

    PageResult<MemberInfoRespVO> convertPage(PageResult<MemberInfoDO> page);

    List<MemberInfoExcelVO> convertList02(List<MemberInfoDO> list);

}
