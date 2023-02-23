package cn.iocoder.yudao.module.hotel.service.folioinfo;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.hotel.controller.admin.folioinfo.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.folioinfo.FolioInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.hotel.convert.folioinfo.FolioInfoConvert;
import cn.iocoder.yudao.module.hotel.dal.mysql.folioinfo.FolioInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.*;

/**
 * 房单信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class FolioInfoServiceImpl implements FolioInfoService {

    @Resource
    private FolioInfoMapper folioInfoMapper;

    @Override
    public Long createFolioInfo(FolioInfoCreateReqVO createReqVO) {
        // 插入
        FolioInfoDO folioInfo = FolioInfoConvert.INSTANCE.convert(createReqVO);
        folioInfoMapper.insert(folioInfo);
        // 返回
        return folioInfo.getId();
    }

    @Override
    public void updateFolioInfo(FolioInfoUpdateReqVO updateReqVO) {
        // 校验存在
        validateFolioInfoExists(updateReqVO.getId());
        // 更新
        FolioInfoDO updateObj = FolioInfoConvert.INSTANCE.convert(updateReqVO);
        folioInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteFolioInfo(Long id) {
        // 校验存在
        validateFolioInfoExists(id);
        // 删除
        folioInfoMapper.deleteById(id);
    }

    private void validateFolioInfoExists(Long id) {
        if (folioInfoMapper.selectById(id) == null) {
            throw exception(FOLIO_INFO_NOT_EXISTS);
        }
    }

    @Override
    public FolioInfoDO getFolioInfo(Long id) {
        return folioInfoMapper.selectById(id);
    }

    @Override
    public List<FolioInfoDO> getFolioInfoList(Collection<Long> ids) {
        return folioInfoMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<FolioInfoDO> getFolioInfoPage(FolioInfoPageReqVO pageReqVO) {
        return folioInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<FolioInfoDO> getFolioInfoList(FolioInfoExportReqVO exportReqVO) {
        return folioInfoMapper.selectList(exportReqVO);
    }

}
