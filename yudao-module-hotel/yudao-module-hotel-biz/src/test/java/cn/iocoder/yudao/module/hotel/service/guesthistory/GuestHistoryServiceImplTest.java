package cn.iocoder.yudao.module.hotel.service.guesthistory;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.hotel.controller.admin.guesthistory.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.guesthistory.GuestHistoryDO;
import cn.iocoder.yudao.module.hotel.dal.mysql.guesthistory.GuestHistoryMapper;
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
* {@link GuestHistoryServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(GuestHistoryServiceImpl.class)
public class GuestHistoryServiceImplTest extends BaseDbUnitTest {

    @Resource
    private GuestHistoryServiceImpl guestHistoryService;

    @Resource
    private GuestHistoryMapper guestHistoryMapper;

    @Test
    public void testCreateGuestHistory_success() {
        // 准备参数
        GuestHistoryCreateReqVO reqVO = randomPojo(GuestHistoryCreateReqVO.class);

        // 调用
        Long guestHistoryId = guestHistoryService.createGuestHistory(reqVO);
        // 断言
        assertNotNull(guestHistoryId);
        // 校验记录的属性是否正确
        GuestHistoryDO guestHistory = guestHistoryMapper.selectById(guestHistoryId);
        assertPojoEquals(reqVO, guestHistory);
    }

    @Test
    public void testUpdateGuestHistory_success() {
        // mock 数据
        GuestHistoryDO dbGuestHistory = randomPojo(GuestHistoryDO.class);
        guestHistoryMapper.insert(dbGuestHistory);// @Sql: 先插入出一条存在的数据
        // 准备参数
        GuestHistoryUpdateReqVO reqVO = randomPojo(GuestHistoryUpdateReqVO.class, o -> {
            o.setId(dbGuestHistory.getId()); // 设置更新的 ID
        });

        // 调用
        guestHistoryService.updateGuestHistory(reqVO);
        // 校验是否更新正确
        GuestHistoryDO guestHistory = guestHistoryMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, guestHistory);
    }

    @Test
    public void testUpdateGuestHistory_notExists() {
        // 准备参数
        GuestHistoryUpdateReqVO reqVO = randomPojo(GuestHistoryUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> guestHistoryService.updateGuestHistory(reqVO), GUEST_HISTORY_NOT_EXISTS);
    }

    @Test
    public void testDeleteGuestHistory_success() {
        // mock 数据
        GuestHistoryDO dbGuestHistory = randomPojo(GuestHistoryDO.class);
        guestHistoryMapper.insert(dbGuestHistory);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbGuestHistory.getId();

        // 调用
        guestHistoryService.deleteGuestHistory(id);
       // 校验数据不存在了
       assertNull(guestHistoryMapper.selectById(id));
    }

    @Test
    public void testDeleteGuestHistory_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> guestHistoryService.deleteGuestHistory(id), GUEST_HISTORY_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetGuestHistoryPage() {
       // mock 数据
       GuestHistoryDO dbGuestHistory = randomPojo(GuestHistoryDO.class, o -> { // 等会查询到
           o.setGuestName(null);
           o.setPhone(null);
           o.setCardNo(null);
           o.setMemberId(null);
           o.setLatestCheckin(null);
           o.setCheckinNum(null);
       });
       guestHistoryMapper.insert(dbGuestHistory);
       // 测试 guestName 不匹配
       guestHistoryMapper.insert(cloneIgnoreId(dbGuestHistory, o -> o.setGuestName(null)));
       // 测试 phone 不匹配
       guestHistoryMapper.insert(cloneIgnoreId(dbGuestHistory, o -> o.setPhone(null)));
       // 测试 cardNo 不匹配
       guestHistoryMapper.insert(cloneIgnoreId(dbGuestHistory, o -> o.setCardNo(null)));
       // 测试 memberId 不匹配
       guestHistoryMapper.insert(cloneIgnoreId(dbGuestHistory, o -> o.setMemberId(null)));
       // 测试 latestCheckin 不匹配
       guestHistoryMapper.insert(cloneIgnoreId(dbGuestHistory, o -> o.setLatestCheckin(null)));
       // 测试 checkinNum 不匹配
       guestHistoryMapper.insert(cloneIgnoreId(dbGuestHistory, o -> o.setCheckinNum(null)));
       // 准备参数
       GuestHistoryPageReqVO reqVO = new GuestHistoryPageReqVO();
       reqVO.setGuestId(null);
       reqVO.setGuestName(null);
       reqVO.setPhone(null);
       reqVO.setCardNo(null);
       reqVO.setMemberId(null);
       reqVO.setLatestCheckin(null);
       reqVO.setCheckinNum(null);

       // 调用
       PageResult<GuestHistoryDO> pageResult = guestHistoryService.getGuestHistoryPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbGuestHistory, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetGuestHistoryList() {
       // mock 数据
       GuestHistoryDO dbGuestHistory = randomPojo(GuestHistoryDO.class, o -> { // 等会查询到
           o.setGuestName(null);
           o.setPhone(null);
           o.setCardNo(null);
           o.setMemberId(null);
           o.setLatestCheckin(null);
           o.setCheckinNum(null);
       });
       guestHistoryMapper.insert(dbGuestHistory);
       // 测试 guestName 不匹配
       guestHistoryMapper.insert(cloneIgnoreId(dbGuestHistory, o -> o.setGuestName(null)));
       // 测试 phone 不匹配
       guestHistoryMapper.insert(cloneIgnoreId(dbGuestHistory, o -> o.setPhone(null)));
       // 测试 cardNo 不匹配
       guestHistoryMapper.insert(cloneIgnoreId(dbGuestHistory, o -> o.setCardNo(null)));
       // 测试 memberId 不匹配
       guestHistoryMapper.insert(cloneIgnoreId(dbGuestHistory, o -> o.setMemberId(null)));
       // 测试 latestCheckin 不匹配
       guestHistoryMapper.insert(cloneIgnoreId(dbGuestHistory, o -> o.setLatestCheckin(null)));
       // 测试 checkinNum 不匹配
       guestHistoryMapper.insert(cloneIgnoreId(dbGuestHistory, o -> o.setCheckinNum(null)));
       // 准备参数
       GuestHistoryExportReqVO reqVO = new GuestHistoryExportReqVO();
       reqVO.setGuestName(null);
       reqVO.setPhone(null);
       reqVO.setCardNo(null);
       reqVO.setMemberId(null);
       reqVO.setLatestCheckin(null);
       reqVO.setCheckinNum(null);

       // 调用
       List<GuestHistoryDO> list = guestHistoryService.getGuestHistoryList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbGuestHistory, list.get(0));
    }

}
