package cn.iocoder.yudao.module.hotel.controller.admin.company;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.hotel.controller.admin.company.vo.*;
import cn.iocoder.yudao.module.hotel.convert.company.CompanyConvert;
import cn.iocoder.yudao.module.hotel.dal.dataobject.company.CompanyDO;
import cn.iocoder.yudao.module.hotel.service.company.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 公司信息")
@RestController
@RequestMapping("/hotel/company")
@Validated
public class CompanyController {

    @Resource
    private CompanyService companyService;

    @PostMapping("/create")
    @Operation(summary = "创建公司信息")
    @PreAuthorize("@ss.hasPermission('hotel:company:create')")
    public CommonResult<Long> createCompany(@Valid @RequestBody CompanyCreateReqVO createReqVO) {
        return success(companyService.createCompany(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新公司信息")
    @PreAuthorize("@ss.hasPermission('hotel:company:update')")
    public CommonResult<Boolean> updateCompany(@Valid @RequestBody CompanyUpdateReqVO updateReqVO) {
        companyService.updateCompany(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除公司信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hotel:company:delete')")
    public CommonResult<Boolean> deleteCompany(@RequestParam("id") Long id) {
        companyService.deleteCompany(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得公司信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hotel:company:query')")
    public CommonResult<CompanyRespVO> getCompany(@RequestParam("id") Long id) {
        CompanyDO company = companyService.getCompany(id);
        return success(CompanyConvert.INSTANCE.convert(company));
    }

    @GetMapping("/list")
    @Operation(summary = "获得公司信息列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hotel:company:query')")
    public CommonResult<List<CompanyRespVO>> getCompanyList(@RequestParam("ids") Collection<Long> ids) {
        List<CompanyDO> list = companyService.getCompanyList(ids);
        return success(CompanyConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得公司信息分页")
    @PreAuthorize("@ss.hasPermission('hotel:company:query')")
    public CommonResult<PageResult<CompanyRespVO>> getCompanyPage(@Valid CompanyPageReqVO pageVO) {
        PageResult<CompanyDO> pageResult = companyService.getCompanyPage(pageVO);
        return success(CompanyConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出公司信息 Excel")
    @PreAuthorize("@ss.hasPermission('hotel:company:export')")
    @OperateLog(type = EXPORT)
    public void exportCompanyExcel(@Valid CompanyExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CompanyDO> list = companyService.getCompanyList(exportReqVO);
        // 导出 Excel
        List<CompanyExcelVO> datas = CompanyConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "公司信息.xls", "数据", CompanyExcelVO.class, datas);
    }

}
