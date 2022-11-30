package cn.iocoder.yudao.module.hotel.controller.admin.memberinfo;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;

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

import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberinfo.MemberInfoDO;
import cn.iocoder.yudao.module.hotel.convert.memberinfo.MemberInfoConvert;
import cn.iocoder.yudao.module.hotel.service.memberinfo.MemberInfoService;

@Api(tags = "管理后台 - 会员信息")
@RestController
@RequestMapping("/hotel/member-info")
@Validated
public class MemberInfoController {

    @Resource
    private MemberInfoService memberInfoService;

    @PostMapping("/create")
    @ApiOperation("创建会员信息")
    @PreAuthorize("@ss.hasPermission('hotel:member-info:create')")
    public CommonResult<Long> createMemberInfo(@Valid @RequestBody MemberInfoCreateReqVO createReqVO) {
        return success(memberInfoService.createMemberInfo(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新会员信息")
    @PreAuthorize("@ss.hasPermission('hotel:member-info:update')")
    public CommonResult<Boolean> updateMemberInfo(@Valid @RequestBody MemberInfoUpdateReqVO updateReqVO) {
        memberInfoService.updateMemberInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除会员信息")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('hotel:member-info:delete')")
    public CommonResult<Boolean> deleteMemberInfo(@RequestParam("id") Long id) {
        memberInfoService.deleteMemberInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得会员信息")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('hotel:member-info:query')")
    public CommonResult<MemberInfoRespVO> getMemberInfo(@RequestParam("id") Long id) {
        MemberInfoDO memberInfo = memberInfoService.getMemberInfo(id);
        return success(MemberInfoConvert.INSTANCE.convert(memberInfo));
    }

    @GetMapping("/list")
    @ApiOperation("获得会员信息列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('hotel:member-info:query')")
    public CommonResult<List<MemberInfoRespVO>> getMemberInfoList(@RequestParam("ids") Collection<Long> ids) {
        List<MemberInfoDO> list = memberInfoService.getMemberInfoList(ids);
        return success(MemberInfoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得会员信息分页")
    @PreAuthorize("@ss.hasPermission('hotel:member-info:query')")
    public CommonResult<PageResult<MemberInfoRespVO>> getMemberInfoPage(@Valid MemberInfoPageReqVO pageVO) {
        PageResult<MemberInfoDO> pageResult = memberInfoService.getMemberInfoPage(pageVO);
        return success(MemberInfoConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出会员信息 Excel")
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
