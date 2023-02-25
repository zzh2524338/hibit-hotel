package cn.iocoder.yudao.module.hotel.service.memberinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoPageReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoUpdateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.memberinfo.vo.MemberInfoVO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberinfo.MemberInfoDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 会员信息 Service 接口
 *
 * @author hibit
 */
public interface MemberInfoService {

    /**
     * 创建会员信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMemberInfo(@Valid MemberInfoCreateReqVO createReqVO);

    /**
     * 更新会员信息
     *
     * @param updateReqVO 更新信息
     */
    void updateMemberInfo(@Valid MemberInfoUpdateReqVO updateReqVO);

    /**
     * 删除会员信息
     *
     * @param id 编号
     */
    void deleteMemberInfo(Long id);

    /**
     * 获得会员信息
     *
     * @param id 编号
     * @return 会员信息
     */
    MemberInfoDO getMemberInfo(Long id);

    /**
     * 根据手机号查询获得会员信息
     *
     * @param number 手机号
     * @return 会员信息
     */
    MemberInfoDO getMemberInfoByPhone(String number);

    /**
     * 获得会员信息列表
     *
     * @param ids 编号
     * @return 会员信息列表
     */
    List<MemberInfoDO> getMemberInfoList(Collection<Long> ids);

    /**
     * 获得会员信息分页
     *
     * @param pageReqVO 分页查询
     * @return 会员信息分页
     */
    PageResult<MemberInfoDO> getMemberInfoPage(MemberInfoPageReqVO pageReqVO);

    /**
     * 获得会员信息分页
     *
     * @param pageReqVO 分页查询
     * @return 会员信息分页
     */
    PageResult<MemberInfoVO> getMemberInfoPageByName(MemberInfoPageReqVO pageReqVO);

    /**
     * 获得会员信息列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 会员信息列表
     */
    List<MemberInfoDO> getMemberInfoList(MemberInfoExportReqVO exportReqVO);

}
