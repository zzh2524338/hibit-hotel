package cn.iocoder.yudao.module.hotel.convert.building;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hotel.controller.admin.building.vo.BuildingCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.building.vo.BuildingExcelVO;
import cn.iocoder.yudao.module.hotel.controller.admin.building.vo.BuildingRespVO;
import cn.iocoder.yudao.module.hotel.controller.admin.building.vo.BuildingUpdateReqVO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.building.BuildingDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
