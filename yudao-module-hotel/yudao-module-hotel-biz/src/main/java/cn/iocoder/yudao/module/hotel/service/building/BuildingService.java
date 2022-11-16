package cn.iocoder.yudao.module.hotel.service.building;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.hotel.controller.admin.building.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.building.BuildingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 公司分部 Service 接口
 *
 * @author 芋道源码
 */
public interface BuildingService {

    /**
     * 创建公司分部
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBuilding(@Valid BuildingCreateReqVO createReqVO);

    /**
     * 更新公司分部
     *
     * @param updateReqVO 更新信息
     */
    void updateBuilding(@Valid BuildingUpdateReqVO updateReqVO);

    /**
     * 删除公司分部
     *
     * @param id 编号
     */
    void deleteBuilding(Long id);

    /**
     * 获得公司分部
     *
     * @param id 编号
     * @return 公司分部
     */
    BuildingDO getBuilding(Long id);

    /**
     * 获得公司分部列表
     *
     * @param ids 编号
     * @return 公司分部列表
     */
    List<BuildingDO> getBuildingList(Collection<Long> ids);

    /**
     * 获得公司分部分页
     *
     * @param pageReqVO 分页查询
     * @return 公司分部分页
     */
    PageResult<BuildingDO> getBuildingPage(BuildingPageReqVO pageReqVO);

    /**
     * 获得公司分部列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 公司分部列表
     */
    List<BuildingDO> getBuildingList(BuildingExportReqVO exportReqVO);

}
