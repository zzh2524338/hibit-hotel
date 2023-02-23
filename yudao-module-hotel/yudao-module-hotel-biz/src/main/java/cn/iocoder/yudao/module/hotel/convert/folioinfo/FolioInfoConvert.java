package cn.iocoder.yudao.module.hotel.convert.folioinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.hotel.controller.admin.folioinfo.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.folioinfo.FolioInfoDO;

/**
 * 房单信息 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface FolioInfoConvert {

    FolioInfoConvert INSTANCE = Mappers.getMapper(FolioInfoConvert.class);

    FolioInfoDO convert(FolioInfoCreateReqVO bean);

    FolioInfoDO convert(FolioInfoUpdateReqVO bean);

    FolioInfoRespVO convert(FolioInfoDO bean);

    List<FolioInfoRespVO> convertList(List<FolioInfoDO> list);

    PageResult<FolioInfoRespVO> convertPage(PageResult<FolioInfoDO> page);

    List<FolioInfoExcelVO> convertList02(List<FolioInfoDO> list);

}
