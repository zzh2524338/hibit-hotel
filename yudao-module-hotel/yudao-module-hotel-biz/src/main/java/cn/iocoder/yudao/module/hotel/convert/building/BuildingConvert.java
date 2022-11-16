package cn.iocoder.yudao.module.hotel.convert.building;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.hotel.controller.admin.building.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.building.BuildingDO;

/**
 * 公司分部 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface BuildingConvert {

    BuildingConvert INSTANCE = Mappers.getMapper(BuildingConvert.class);

    BuildingDO convert(BuildingCreateReqVO bean);

    BuildingDO convert(BuildingUpdateReqVO bean);

    BuildingRespVO convert(BuildingDO bean);

    List<BuildingRespVO> convertList(List<BuildingDO> list);

    PageResult<BuildingRespVO> convertPage(PageResult<BuildingDO> page);

    List<BuildingExcelVO> convertList02(List<BuildingDO> list);

}
