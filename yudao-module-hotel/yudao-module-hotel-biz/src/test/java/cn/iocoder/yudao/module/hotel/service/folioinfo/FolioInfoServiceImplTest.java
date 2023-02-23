package cn.iocoder.yudao.module.hotel.service.folioinfo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.hotel.controller.admin.folioinfo.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.folioinfo.FolioInfoDO;
import cn.iocoder.yudao.module.hotel.dal.mysql.folioinfo.FolioInfoMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link FolioInfoServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(FolioInfoServiceImpl.class)
public class FolioInfoServiceImplTest extends BaseDbUnitTest {

    @Resource
    private FolioInfoServiceImpl folioInfoService;

    @Resource
    private FolioInfoMapper folioInfoMapper;

    @Test
    public void testCreateFolioInfo_success() {
        // 准备参数
        FolioInfoCreateReqVO reqVO = randomPojo(FolioInfoCreateReqVO.class);

        // 调用
        Long folioInfoId = folioInfoService.createFolioInfo(reqVO);
        // 断言
        assertNotNull(folioInfoId);
        // 校验记录的属性是否正确
        FolioInfoDO folioInfo = folioInfoMapper.selectById(folioInfoId);
        assertPojoEquals(reqVO, folioInfo);
    }

    @Test
    public void testUpdateFolioInfo_success() {
        // mock 数据
        FolioInfoDO dbFolioInfo = randomPojo(FolioInfoDO.class);
        folioInfoMapper.insert(dbFolioInfo);// @Sql: 先插入出一条存在的数据
        // 准备参数
        FolioInfoUpdateReqVO reqVO = randomPojo(FolioInfoUpdateReqVO.class, o -> {
            o.setId(dbFolioInfo.getId()); // 设置更新的 ID
        });

        // 调用
        folioInfoService.updateFolioInfo(reqVO);
        // 校验是否更新正确
        FolioInfoDO folioInfo = folioInfoMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, folioInfo);
    }

    @Test
    public void testUpdateFolioInfo_notExists() {
        // 准备参数
        FolioInfoUpdateReqVO reqVO = randomPojo(FolioInfoUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> folioInfoService.updateFolioInfo(reqVO), FOLIO_INFO_NOT_EXISTS);
    }

    @Test
    public void testDeleteFolioInfo_success() {
        // mock 数据
        FolioInfoDO dbFolioInfo = randomPojo(FolioInfoDO.class);
        folioInfoMapper.insert(dbFolioInfo);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbFolioInfo.getId();

        // 调用
        folioInfoService.deleteFolioInfo(id);
       // 校验数据不存在了
       assertNull(folioInfoMapper.selectById(id));
    }

    @Test
    public void testDeleteFolioInfo_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> folioInfoService.deleteFolioInfo(id), FOLIO_INFO_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFolioInfoPage() {
       // mock 数据
       FolioInfoDO dbFolioInfo = randomPojo(FolioInfoDO.class, o -> { // 等会查询到
           o.setOrderId(null);
           o.setRoomTypeId(null);
           o.setRoomId(null);
           o.setStatus(null);
           o.setArrivalTime(null);
           o.setDepartTime(null);
       });
       folioInfoMapper.insert(dbFolioInfo);
       // 测试 orderId 不匹配
       folioInfoMapper.insert(cloneIgnoreId(dbFolioInfo, o -> o.setOrderId(null)));
       // 测试 roomTypeId 不匹配
       folioInfoMapper.insert(cloneIgnoreId(dbFolioInfo, o -> o.setRoomTypeId(null)));
       // 测试 roomId 不匹配
       folioInfoMapper.insert(cloneIgnoreId(dbFolioInfo, o -> o.setRoomId(null)));
       // 测试 status 不匹配
       folioInfoMapper.insert(cloneIgnoreId(dbFolioInfo, o -> o.setStatus(null)));
       // 测试 arrivalTime 不匹配
       folioInfoMapper.insert(cloneIgnoreId(dbFolioInfo, o -> o.setArrivalTime(null)));
       // 测试 departTime 不匹配
       folioInfoMapper.insert(cloneIgnoreId(dbFolioInfo, o -> o.setDepartTime(null)));
       // 准备参数
       FolioInfoPageReqVO reqVO = new FolioInfoPageReqVO();
       reqVO.setOrderId(null);
       reqVO.setRoomTypeId(null);
       reqVO.setRoomId(null);
       reqVO.setStatus(null);
       reqVO.setArrivalTime(null);
       reqVO.setDepartTime(null);

       // 调用
       PageResult<FolioInfoDO> pageResult = folioInfoService.getFolioInfoPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbFolioInfo, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFolioInfoList() {
       // mock 数据
       FolioInfoDO dbFolioInfo = randomPojo(FolioInfoDO.class, o -> { // 等会查询到
           o.setOrderId(null);
           o.setRoomTypeId(null);
           o.setRoomId(null);
           o.setStatus(null);
           o.setArrivalTime(null);
           o.setDepartTime(null);
       });
       folioInfoMapper.insert(dbFolioInfo);
       // 测试 orderId 不匹配
       folioInfoMapper.insert(cloneIgnoreId(dbFolioInfo, o -> o.setOrderId(null)));
       // 测试 roomTypeId 不匹配
       folioInfoMapper.insert(cloneIgnoreId(dbFolioInfo, o -> o.setRoomTypeId(null)));
       // 测试 roomId 不匹配
       folioInfoMapper.insert(cloneIgnoreId(dbFolioInfo, o -> o.setRoomId(null)));
       // 测试 status 不匹配
       folioInfoMapper.insert(cloneIgnoreId(dbFolioInfo, o -> o.setStatus(null)));
       // 测试 arrivalTime 不匹配
       folioInfoMapper.insert(cloneIgnoreId(dbFolioInfo, o -> o.setArrivalTime(null)));
       // 测试 departTime 不匹配
       folioInfoMapper.insert(cloneIgnoreId(dbFolioInfo, o -> o.setDepartTime(null)));
       // 准备参数
       FolioInfoExportReqVO reqVO = new FolioInfoExportReqVO();
       reqVO.setOrderId(null);
       reqVO.setRoomTypeId(null);
       reqVO.setRoomId(null);
       reqVO.setStatus(null);
       reqVO.setArrivalTime(null);
       reqVO.setDepartTime(null);

       // 调用
       List<FolioInfoDO> list = folioInfoService.getFolioInfoList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbFolioInfo, list.get(0));
    }

}
