package cn.iocoder.yudao.module.hotel.controller.admin.memberinfo;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoExcelVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoPageReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoRespVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoUpdateReqVO;
import cn.iocoder.yudao.module.hotel.convert.memberinfo.MemberInfoConvert;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberinfo.MemberInfoDO;
import cn.iocoder.yudao.module.hotel.service.memberinfo.MemberInfoService;
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

@Tag(name = "管理后台 - 会员信息")
@RestController
@RequestMapping("/hotel/member-info")
@Validated
public class MemberInfoController {

    @Resource
    private MemberInfoService memberInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建会员信息")
    @PreAuthorize("@ss.hasPermission('hotel:member-info:create')")
    public CommonResult<Long> createMemberInfo(@Valid @RequestBody MemberInfoCreateReqVO createReqVO) {
        return success(memberInfoService.createMemberInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会员信息")
    @PreAuthorize("@ss.hasPermission('hotel:member-info:update')")
    public CommonResult<Boolean> updateMemberInfo(@Valid @RequestBody MemberInfoUpdateReqVO updateReqVO) {
        memberInfoService.updateMemberInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除会员信息")
    @Parameter(name = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hotel:member-info:delete')")
    public CommonResult<Boolean> deleteMemberInfo(@RequestParam("id") Long id) {
        memberInfoService.deleteMemberInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得会员信息")
    @Parameter(name = "id 编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hotel:member-info:query')")
    public CommonResult<MemberInfoRespVO> getMemberInfo(@RequestParam("id") Long id) {
        MemberInfoDO memberInfo = memberInfoService.getMemberInfo(id);
        return success(MemberInfoConvert.INSTANCE.convert(memberInfo));
    }

    @GetMapping("/list")
    @Operation(summary = "获得会员信息列表")
    @Parameter(name = "ids 编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('hotel:member-info:query')")
    public CommonResult<List<MemberInfoRespVO>> getMemberInfoList(@RequestParam("ids") Collection<Long> ids) {
        List<MemberInfoDO> list = memberInfoService.getMemberInfoList(ids);
        return success(MemberInfoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得会员信息分页")
    @PreAuthorize("@ss.hasPermission('hotel:member-info:query')")
    public CommonResult<PageResult<MemberInfoRespVO>> getMemberInfoPage(@Valid MemberInfoPageReqVO pageVO) {
        PageResult<MemberInfoDO> pageResult = memberInfoService.getMemberInfoPage(pageVO);
        return success(MemberInfoConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出会员信息 Excel")
    @PreAuthorize("@ss.hasPermission('hotel:member-info:export')")
    @OperateLog(type = EXPORT)
    public void exportMemberInfoExcel(@Valid MemberInfoExportReqVO exportReqVO,
            HttpServletResponse response) throws IOException {
        List<MemberInfoDO> list = memberInfoService.getMemberInfoList(exportReqVO);
        // 导出 Excel
        List<MemberInfoExcelVO> datas = MemberInfoConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "会员信息.xls", "数据", MemberInfoExcelVO.class, datas);
    }

}
