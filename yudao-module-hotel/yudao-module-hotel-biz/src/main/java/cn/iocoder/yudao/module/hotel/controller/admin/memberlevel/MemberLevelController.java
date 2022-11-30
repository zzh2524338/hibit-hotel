package cn.iocoder.yudao.module.hotel.controller.admin.memberlevel;

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

import cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberlevel.MemberLevelDO;
import cn.iocoder.yudao.module.hotel.convert.memberlevel.MemberLevelConvert;
import cn.iocoder.yudao.module.hotel.service.memberlevel.MemberLevelService;

@Api(tags = "管理后台 - 会员等级")
@RestController
@RequestMapping("/hotel/member-level")
@Validated
public class MemberLevelController {

    @Resource
    private MemberLevelService memberLevelService;

    @PostMapping("/create")
    @ApiOperation("创建会员等级")
    @PreAuthorize("@ss.hasPermission('hotel:member-level:create')")
    public CommonResult<Long> createMemberLevel(@Valid @RequestBody MemberLevelCreateReqVO createReqVO) {
        return success(memberLevelService.createMemberLevel(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新会员等级")
    @PreAuthorize("@ss.hasPermission('hotel:member-level:update')")
    public CommonResult<Boolean> updateMemberLevel(@Valid @RequestBody MemberLevelUpdateReqVO updateReqVO) {
        memberLevelService.updateMemberLevel(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除会员等级")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('hotel:member-level:delete')")
    public CommonResult<Boolean> deleteMemberLevel(@RequestParam("id") Long id) {
        memberLevelService.deleteMemberLevel(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得会员等级")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('hotel:member-level:query')")
    public CommonResult<MemberLevelRespVO> getMemberLevel(@RequestParam("id") Long id) {
        MemberLevelDO memberLevel = memberLevelService.getMemberLevel(id);
        return success(MemberLevelConvert.INSTANCE.convert(memberLevel));
    }


    @GetMapping("/list/all")
    @ApiOperation("获得会员等级列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('hotel:member-level:query')")
    public CommonResult<List<MemberLevelRespVO>> getAllMemberLevelList() {
        List<MemberLevelDO> list = memberLevelService.getAllMemberLevelList();
        return success(MemberLevelConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/list/ids")
    @ApiOperation("获得会员等级列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('hotel:member-level:query')")
    public CommonResult<List<MemberLevelRespVO>> getMemberLevelList(@RequestParam("ids") Collection<Long> ids) {
        List<MemberLevelDO> list = memberLevelService.getMemberLevelList(ids);
        return success(MemberLevelConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得会员等级分页")
    @PreAuthorize("@ss.hasPermission('hotel:member-level:query')")
    public CommonResult<PageResult<MemberLevelRespVO>> getMemberLevelPage(@Valid MemberLevelPageReqVO pageVO) {
        PageResult<MemberLevelDO> pageResult = memberLevelService.getMemberLevelPage(pageVO);
        return success(MemberLevelConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出会员等级 Excel")
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
