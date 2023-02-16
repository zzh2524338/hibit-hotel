package cn.iocoder.yudao.module.hotel.controller.admin.building;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.hotel.controller.admin.building.vo.BuildingCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.building.vo.BuildingExcelVO;
import cn.iocoder.yudao.module.hotel.controller.admin.building.vo.BuildingExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.building.vo.BuildingPageReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.building.vo.BuildingRespVO;
import cn.iocoder.yudao.module.hotel.controller.admin.building.vo.BuildingUpdateReqVO;
import cn.iocoder.yudao.module.hotel.convert.building.BuildingConvert;
import cn.iocoder.yudao.module.hotel.dal.dataobject.building.BuildingDO;
import cn.iocoder.yudao.module.hotel.service.building.BuildingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 公司分部")
@RestController
@RequestMapping("/hotel/building")
@Validated
public class BuildingController {

    @Resource
    private BuildingService buildingService;

    @PostMapping("/create")
    @Operation(summary = "创建公司分部")
    @PreAuthorize("@ss.hasPermission('hotel:building:create')")
    public CommonResult<Long> createBuilding(@Valid @RequestBody BuildingCreateReqVO createReqVO) {
        return success(buildingService.createBuilding(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新公司分部")
    @PreAuthorize("@ss.hasPermission('hotel:building:update')")
    public CommonResult<Boolean> updateBuilding(@Valid @RequestBody BuildingUpdateReqVO updateReqVO) {
        buildingService.updateBuilding(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除公司分部")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hotel:building:delete')")
    public CommonResult<Boolean> deleteBuilding(@RequestParam("id") Long id) {
        buildingService.deleteBuilding(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得公司分部")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hotel:building:query')")
    public CommonResult<BuildingRespVO> getBuilding(@RequestParam("id") Long id) {
        BuildingDO building = buildingService.getBuilding(id);
        return success(BuildingConvert.INSTANCE.convert(building));
    }

    @GetMapping("/list")
    @Operation(summary = "获得公司分部列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hotel:building:query')")
    public CommonResult<List<BuildingRespVO>> getBuildingList(@RequestParam("ids") Collection<Long> ids) {
        List<BuildingDO> list = buildingService.getBuildingList(ids);
        return success(BuildingConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得公司分部分页")
    @PreAuthorize("@ss.hasPermission('hotel:building:query')")
    public CommonResult<PageResult<BuildingRespVO>> getBuildingPage(@Valid BuildingPageReqVO pageVO) {
        PageResult<BuildingDO> pageResult = buildingService.getBuildingPage(pageVO);
        return success(BuildingConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出公司分部 Excel")
    @PreAuthorize("@ss.hasPermission('hotel:building:export')")
    @OperateLog(type = EXPORT)
    public void exportBuildingExcel(@Valid BuildingExportReqVO exportReqVO,
            HttpServletResponse response) throws IOException {
        List<BuildingDO> list = buildingService.getBuildingList(exportReqVO);
        // 导出 Excel
        List<BuildingExcelVO> datas = BuildingConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "公司分部.xls", "数据", BuildingExcelVO.class, datas);
    }

}
