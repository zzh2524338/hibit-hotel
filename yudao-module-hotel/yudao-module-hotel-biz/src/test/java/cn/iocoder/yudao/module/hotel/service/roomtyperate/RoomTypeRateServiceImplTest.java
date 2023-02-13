package cn.iocoder.yudao.module.hotel.service.roomtyperate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtyperate.RoomTypeRateDO;
import cn.iocoder.yudao.module.hotel.dal.mysql.roomtyperate.RoomTypeRateMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.buildBetweenTime;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link RoomTypeRateServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(RoomTypeRateServiceImpl.class)
public class RoomTypeRateServiceImplTest extends BaseDbUnitTest {

    @Resource
    private RoomTypeRateServiceImpl roomTypeRateService;

    @Resource
    private RoomTypeRateMapper roomTypeRateMapper;

    @Test
    public void testCreateRoomTypeRate_success() {
        // 准备参数
        RoomTypeRateCreateReqVO reqVO = randomPojo(RoomTypeRateCreateReqVO.class);

        // 调用
        Long roomTypeRateId = roomTypeRateService.createRoomTypeRate(reqVO);
        // 断言
        assertNotNull(roomTypeRateId);
        // 校验记录的属性是否正确
        RoomTypeRateDO roomTypeRate = roomTypeRateMapper.selectById(roomTypeRateId);
        assertPojoEquals(reqVO, roomTypeRate);
    }

    @Test
    public void testUpdateRoomTypeRate_success() {
        // mock 数据
        RoomTypeRateDO dbRoomTypeRate = randomPojo(RoomTypeRateDO.class);
        roomTypeRateMapper.insert(dbRoomTypeRate);// @Sql: 先插入出一条存在的数据
        // 准备参数
        RoomTypeRateUpdateReqVO reqVO = randomPojo(RoomTypeRateUpdateReqVO.class, o -> {
            o.setId(dbRoomTypeRate.getId()); // 设置更新的 ID
        });

        // 调用
        roomTypeRateService.updateRoomTypeRate(reqVO);
        // 校验是否更新正确
        RoomTypeRateDO roomTypeRate = roomTypeRateMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, roomTypeRate);
    }

    @Test
    public void testUpdateRoomTypeRate_notExists() {
        // 准备参数
        RoomTypeRateUpdateReqVO reqVO = randomPojo(RoomTypeRateUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> roomTypeRateService.updateRoomTypeRate(reqVO), ROOM_TYPE_RATE_NOT_EXISTS);
    }

    @Test
    public void testDeleteRoomTypeRate_success() {
        // mock 数据
        RoomTypeRateDO dbRoomTypeRate = randomPojo(RoomTypeRateDO.class);
        roomTypeRateMapper.insert(dbRoomTypeRate);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbRoomTypeRate.getId();

        // 调用
        roomTypeRateService.deleteRoomTypeRate(id);
       // 校验数据不存在了
       assertNull(roomTypeRateMapper.selectById(id));
    }

    @Test
    public void testDeleteRoomTypeRate_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> roomTypeRateService.deleteRoomTypeRate(id), ROOM_TYPE_RATE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRoomTypeRatePage() {
       // mock 数据
       RoomTypeRateDO dbRoomTypeRate = randomPojo(RoomTypeRateDO.class, o -> { // 等会查询到
           o.setRoomTypeId(null);
           o.setRoomRateTypeId(null);
           o.setRoomRate(null);
           o.setAccDate(null);
           o.setCreateTime(null);
       });
       roomTypeRateMapper.insert(dbRoomTypeRate);
       // 测试 roomTypeId 不匹配
       roomTypeRateMapper.insert(cloneIgnoreId(dbRoomTypeRate, o -> o.setRoomTypeId(null)));
       // 测试 roomRateTypeId 不匹配
       roomTypeRateMapper.insert(cloneIgnoreId(dbRoomTypeRate, o -> o.setRoomRateTypeId(null)));
       // 测试 roomRate 不匹配
       roomTypeRateMapper.insert(cloneIgnoreId(dbRoomTypeRate, o -> o.setRoomRate(null)));
       // 测试 accDate 不匹配
       roomTypeRateMapper.insert(cloneIgnoreId(dbRoomTypeRate, o -> o.setAccDate(null)));
       // 测试 createTime 不匹配
       roomTypeRateMapper.insert(cloneIgnoreId(dbRoomTypeRate, o -> o.setCreateTime(null)));
       // 准备参数
       RoomTypeRatePageReqVO reqVO = new RoomTypeRatePageReqVO();
       reqVO.setRoomTypeId(null);
       reqVO.setRoomRateTypeId(null);
       reqVO.setRoomRate(null);
       reqVO.setProRate(null);
       reqVO.setAccDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<RoomTypeRateDO> pageResult = roomTypeRateService.getRoomTypeRatePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbRoomTypeRate, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRoomTypeRateList() {
       // mock 数据
       RoomTypeRateDO dbRoomTypeRate = randomPojo(RoomTypeRateDO.class, o -> { // 等会查询到
           o.setRoomTypeId(null);
           o.setRoomRateTypeId(null);
           o.setRoomRate(null);
           o.setAccDate(null);
           o.setCreateTime(null);
       });
       roomTypeRateMapper.insert(dbRoomTypeRate);
       // 测试 roomTypeId 不匹配
       roomTypeRateMapper.insert(cloneIgnoreId(dbRoomTypeRate, o -> o.setRoomTypeId(null)));
       // 测试 roomRateTypeId 不匹配
       roomTypeRateMapper.insert(cloneIgnoreId(dbRoomTypeRate, o -> o.setRoomRateTypeId(null)));
       // 测试 roomRate 不匹配
       roomTypeRateMapper.insert(cloneIgnoreId(dbRoomTypeRate, o -> o.setRoomRate(null)));
       // 测试 accDate 不匹配
       roomTypeRateMapper.insert(cloneIgnoreId(dbRoomTypeRate, o -> o.setAccDate(null)));
       // 测试 createTime 不匹配
       roomTypeRateMapper.insert(cloneIgnoreId(dbRoomTypeRate, o -> o.setCreateTime(null)));
       // 准备参数
       RoomTypeRateExportReqVO reqVO = new RoomTypeRateExportReqVO();
       reqVO.setRoomTypeId(null);
       reqVO.setRoomRateTypeId(null);
       reqVO.setRoomRate(null);
       reqVO.setProRate(null);
       reqVO.setAccDate(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<RoomTypeRateDO> list = roomTypeRateService.getRoomTypeRateList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbRoomTypeRate, list.get(0));
    }

}
