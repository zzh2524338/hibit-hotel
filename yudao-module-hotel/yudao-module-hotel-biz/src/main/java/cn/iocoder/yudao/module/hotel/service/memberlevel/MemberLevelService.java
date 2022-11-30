package cn.iocoder.yudao.module.hotel.service.memberlevel;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.hotel.controller.admin.memberlevel.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberlevel.MemberLevelDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 会员等级 Service 接口
 *
 * @author 芋道源码
 */
public interface MemberLevelService {

    /**
     * 创建会员等级
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMemberLevel(@Valid MemberLevelCreateReqVO createReqVO);

    /**
     * 更新会员等级
     *
     * @param updateReqVO 更新信息
     */
    void updateMemberLevel(@Valid MemberLevelUpdateReqVO updateReqVO);

    /**
     * 删除会员等级
     *
     * @param id 编号
     */
    void deleteMemberLevel(Long id);

    /**
     * 获得会员等级
     *
     * @param id 编号
     * @return 会员等级
     */
    MemberLevelDO getMemberLevel(Long id);

    /**
     * 获得会员等级列表
     *
     * @param ids 编号
     * @return 会员等级列表
     */
    List<MemberLevelDO> getMemberLevelList(Collection<Long> ids);

    /**
     * 获得会员等级列表
     *
     * @return 会员等级列表
     */
    List<MemberLevelDO> getAllMemberLevelList();
    /**
     * 获得会员等级分页
     *
     * @param pageReqVO 分页查询
     * @return 会员等级分页
     */
    PageResult<MemberLevelDO> getMemberLevelPage(MemberLevelPageReqVO pageReqVO);

    /**
     * 获得会员等级列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 会员等级列表
     */
    List<MemberLevelDO> getMemberLevelList(MemberLevelExportReqVO exportReqVO);

}
