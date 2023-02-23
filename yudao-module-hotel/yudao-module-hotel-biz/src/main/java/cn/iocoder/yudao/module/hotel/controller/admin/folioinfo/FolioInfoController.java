package cn.iocoder.yudao.module.hotel.controller.admin.folioinfo;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.hotel.controller.admin.folioinfo.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.folioinfo.FolioInfoDO;
import cn.iocoder.yudao.module.hotel.convert.folioinfo.FolioInfoConvert;
import cn.iocoder.yudao.module.hotel.service.folioinfo.FolioInfoService;

@Tag(name = "管理后台 - 房单信息")
@RestController
@RequestMapping("/hotel/folio-info")
@Validated
public class FolioInfoController {

    @Resource
    private FolioInfoService folioInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建房单信息")
    @PreAuthorize("@ss.hasPermission('hotel:folio-info:create')")
    public CommonResult<Long> createFolioInfo(@Valid @RequestBody FolioInfoCreateReqVO createReqVO) {
        return success(folioInfoService.createFolioInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新房单信息")
    @PreAuthorize("@ss.hasPermission('hotel:folio-info:update')")
    public CommonResult<Boolean> updateFolioInfo(@Valid @RequestBody FolioInfoUpdateReqVO updateReqVO) {
        folioInfoService.updateFolioInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除房单信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hotel:folio-info:delete')")
    public CommonResult<Boolean> deleteFolioInfo(@RequestParam("id") Long id) {
        folioInfoService.deleteFolioInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得房单信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hotel:folio-info:query')")
    public CommonResult<FolioInfoRespVO> getFolioInfo(@RequestParam("id") Long id) {
        FolioInfoDO folioInfo = folioInfoService.getFolioInfo(id);
        return success(FolioInfoConvert.INSTANCE.convert(folioInfo));
    }

    @GetMapping("/list")
    @Operation(summary = "获得房单信息列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hotel:folio-info:query')")
    public CommonResult<List<FolioInfoRespVO>> getFolioInfoList(@RequestParam("ids") Collection<Long> ids) {
        List<FolioInfoDO> list = folioInfoService.getFolioInfoList(ids);
        return success(FolioInfoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得房单信息分页")
    @PreAuthorize("@ss.hasPermission('hotel:folio-info:query')")
    public CommonResult<PageResult<FolioInfoRespVO>> getFolioInfoPage(@Valid FolioInfoPageReqVO pageVO) {
        PageResult<FolioInfoDO> pageResult = folioInfoService.getFolioInfoPage(pageVO);
        return success(FolioInfoConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出房单信息 Excel")
    @PreAuthorize("@ss.hasPermission('hotel:folio-info:export')")
    @OperateLog(type = EXPORT)
    public void exportFolioInfoExcel(@Valid FolioInfoExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<FolioInfoDO> list = folioInfoService.getFolioInfoList(exportReqVO);
        // 导出 Excel
        List<FolioInfoExcelVO> datas = FolioInfoConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "房单信息.xls", "数据", FolioInfoExcelVO.class, datas);
    }

}
