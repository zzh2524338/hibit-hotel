package cn.iocoder.yudao.module.hotel.convert.memberinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoExcelVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoRespVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoUpdateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoVO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberinfo.MemberInfoDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
    MemberInfoVO convert02(MemberInfoDO bean);

    List<MemberInfoRespVO> convertList(List<MemberInfoDO> list);

    PageResult<MemberInfoRespVO> convertPage(PageResult<MemberInfoDO> page);

    List<MemberInfoExcelVO> convertList02(List<MemberInfoDO> list);

}
