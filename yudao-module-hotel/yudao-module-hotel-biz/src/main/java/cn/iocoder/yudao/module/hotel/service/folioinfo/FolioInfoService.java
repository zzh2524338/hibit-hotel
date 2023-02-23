package cn.iocoder.yudao.module.hotel.service.folioinfo;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.hotel.controller.admin.folioinfo.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.folioinfo.FolioInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 房单信息 Service 接口
 *
 * @author 芋道源码
 */
public interface FolioInfoService {

    /**
     * 创建房单信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFolioInfo(@Valid FolioInfoCreateReqVO createReqVO);

    /**
     * 更新房单信息
     *
     * @param updateReqVO 更新信息
     */
    void updateFolioInfo(@Valid FolioInfoUpdateReqVO updateReqVO);

    /**
     * 删除房单信息
     *
     * @param id 编号
     */
    void deleteFolioInfo(Long id);

    /**
     * 获得房单信息
     *
     * @param id 编号
     * @return 房单信息
     */
    FolioInfoDO getFolioInfo(Long id);

    /**
     * 获得房单信息列表
     *
     * @param ids 编号
     * @return 房单信息列表
     */
    List<FolioInfoDO> getFolioInfoList(Collection<Long> ids);

    /**
     * 获得房单信息分页
     *
     * @param pageReqVO 分页查询
     * @return 房单信息分页
     */
    PageResult<FolioInfoDO> getFolioInfoPage(FolioInfoPageReqVO pageReqVO);

    /**
     * 获得房单信息列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 房单信息列表
     */
    List<FolioInfoDO> getFolioInfoList(FolioInfoExportReqVO exportReqVO);

}
