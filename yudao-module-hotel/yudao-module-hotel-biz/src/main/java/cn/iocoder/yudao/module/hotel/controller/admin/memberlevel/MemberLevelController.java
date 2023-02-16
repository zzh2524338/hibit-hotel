package cn.iocoder.yudao.module.hotel.controller.admin.memberlevel;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo.MemberLevelCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo.MemberLevelExcelVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo.MemberLevelExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo.MemberLevelPageReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo.MemberLevelRespVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo.MemberLevelUpdateReqVO;
import cn.iocoder.yudao.module.hotel.convert.memberlevel.MemberLevelConvert;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberlevel.MemberLevelDO;
import cn.iocoder.yudao.module.hotel.service.memberlevel.MemberLevelService;
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

@Tag(name = "管理后台 - 会员等级")
@RestController
@RequestMapping("/hotel/member-level")
@Validated
public class MemberLevelController {

    @Resource
    private MemberLevelService memberLevelService;

    @PostMapping("/create")
    @Operation(summary = "创建会员等级")
    @PreAuthorize("@ss.hasPermission('hotel:member-level:create')")
    public CommonResult<Long> createMemberLevel(@Valid @RequestBody MemberLevelCreateReqVO createReqVO) {
        return success(memberLevelService.createMemberLevel(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会员等级")
    @PreAuthorize("@ss.hasPermission('hotel:member-level:update')")
    public CommonResult<Boolean> updateMemberLevel(@Valid @RequestBody MemberLevelUpdateReqVO updateReqVO) {
        memberLevelService.updateMemberLevel(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除会员等级")
    @Parameter(name = "id 编号", required = true)
    @PreAuthorize("@ss.hasPermission('hotel:member-level:delete')")
    public CommonResult<Boolean> deleteMemberLevel(@RequestParam("id") Long id) {
        memberLevelService.deleteMemberLevel(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得会员等级")
    @Parameter(name = "id 编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hotel:member-level:query')")
    public CommonResult<MemberLevelRespVO> getMemberLevel(@RequestParam("id") Long id) {
        MemberLevelDO memberLevel = memberLevelService.getMemberLevel(id);
        return success(MemberLevelConvert.INSTANCE.convert(memberLevel));
    }


    @GetMapping("/list/all")
    @Operation(summary = "获得会员等级列表")
    @Parameter(name = "ids 编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hotel:member-level:query')")
    public CommonResult<List<MemberLevelRespVO>> getAllMemberLevelList() {
        List<MemberLevelDO> list = memberLevelService.getAllMemberLevelList();
        return success(MemberLevelConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/list/ids")
    @Operation(summary = "获得会员等级列表")
    @Parameter(name = "ids 编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hotel:member-level:query')")
    public CommonResult<List<MemberLevelRespVO>> getMemberLevelList(@RequestParam("ids") Collection<Long> ids) {
        List<MemberLevelDO> list = memberLevelService.getMemberLevelList(ids);
        return success(MemberLevelConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得会员等级分页")
    @PreAuthorize("@ss.hasPermission('hotel:member-level:query')")
    public CommonResult<PageResult<MemberLevelRespVO>> getMemberLevelPage(@Valid MemberLevelPageReqVO pageVO) {
        PageResult<MemberLevelDO> pageResult = memberLevelService.getMemberLevelPage(pageVO);
        return success(MemberLevelConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出会员等级 Excel")
    @PreAuthorize("@ss.hasPermission('hotel:member-level:export')")
    @OperateLog(type = EXPORT)
    public void exportMemberLevelExcel(@Valid MemberLevelExportReqVO exportReqVO,
            HttpServletResponse response) throws IOException {
        List<MemberLevelDO> list = memberLevelService.getMemberLevelList(exportReqVO);
        // 导出 Excel
        List<MemberLevelExcelVO> datas = MemberLevelConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "会员等级.xls", "数据", MemberLevelExcelVO.class, datas);
    }

}
