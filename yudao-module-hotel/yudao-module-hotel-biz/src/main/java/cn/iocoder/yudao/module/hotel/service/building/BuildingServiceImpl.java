package cn.iocoder.yudao.module.hotel.service.building;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.hotel.controller.admin.building.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.building.BuildingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.hotel.convert.building.BuildingConvert;
import cn.iocoder.yudao.module.hotel.dal.mysql.building.BuildingMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.*;

/**
 * 公司分部 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class BuildingServiceImpl implements BuildingService {

    @Resource
    private BuildingMapper buildingMapper;

    @Override
    public Long createBuilding(BuildingCreateReqVO createReqVO) {
        // 插入
        BuildingDO building = BuildingConvert.INSTANCE.convert(createReqVO);
        buildingMapper.insert(building);
        // 返回
        return building.getId();
    }

    @Override
    public void updateBuilding(BuildingUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateBuildingExists(updateReqVO.getId());
        // 更新
        BuildingDO updateObj = BuildingConvert.INSTANCE.convert(updateReqVO);
        buildingMapper.updateById(updateObj);
    }

    @Override
    public void deleteBuilding(Long id) {
        // 校验存在
        this.validateBuildingExists(id);
        // 删除
        buildingMapper.deleteById(id);
    }

    private void validateBuildingExists(Long id) {
        if (buildingMapper.selectById(id) == null) {
            throw exception(BUILDING_NOT_EXISTS);
        }
    }

    @Override
    public BuildingDO getBuilding(Long id) {
        return buildingMapper.selectById(id);
    }

    @Override
    public List<BuildingDO> getBuildingList(Collection<Long> ids) {
        return buildingMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BuildingDO> getBuildingPage(BuildingPageReqVO pageReqVO) {
        return buildingMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BuildingDO> getBuildingList(BuildingExportReqVO exportReqVO) {
        return buildingMapper.selectList(exportReqVO);
    }

}
