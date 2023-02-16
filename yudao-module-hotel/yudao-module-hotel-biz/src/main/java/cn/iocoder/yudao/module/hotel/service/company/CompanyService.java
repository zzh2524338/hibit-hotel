package cn.iocoder.yudao.module.hotel.service.company;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hotel.controller.admin.company.vo.CompanyCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.company.vo.CompanyExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.company.vo.CompanyPageReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.company.vo.CompanyUpdateReqVO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.company.CompanyDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 公司信息 Service 接口
 *
 * @author 芋道源码
 */
public interface CompanyService {

    /**
     * 创建公司信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCompany(@Valid CompanyCreateReqVO createReqVO);

    /**
     * 更新公司信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCompany(@Valid CompanyUpdateReqVO updateReqVO);

    /**
     * 删除公司信息
     *
     * @param id 编号
     */
    void deleteCompany(Long id);

    /**
     * 获得公司信息
     *
     * @param id 编号
     * @return 公司信息
     */
    CompanyDO getCompany(Long id);

    /**
     * 获得公司信息列表
     *
     * @param ids 编号
     * @return 公司信息列表
     */
    List<CompanyDO> getCompanyList(Collection<Long> ids);

    /**
     * 获得公司信息分页
     *
     * @param pageReqVO 分页查询
     * @return 公司信息分页
     */
    PageResult<CompanyDO> getCompanyPage(CompanyPageReqVO pageReqVO);

    /**
     * 获得公司信息列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 公司信息列表
     */
    List<CompanyDO> getCompanyList(CompanyExportReqVO exportReqVO);

}
