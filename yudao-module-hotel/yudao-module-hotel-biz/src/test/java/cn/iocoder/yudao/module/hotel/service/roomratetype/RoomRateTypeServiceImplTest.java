package cn.iocoder.yudao.module.hotel.service.roomratetype;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.hotel.controller.admin.roomratetype.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomratetype.RoomRateTypeDO;
import cn.iocoder.yudao.module.hotel.dal.mysql.roomratetype.RoomRateTypeMapper;
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
* {@link RoomRateTypeServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(RoomRateTypeServiceImpl.class)
public class RoomRateTypeServiceImplTest extends BaseDbUnitTest {

    @Resource
    private RoomRateTypeServiceImpl roomRateTypeService;

    @Resource
    private RoomRateTypeMapper roomRateTypeMapper;

    @Test
    public void testCreateRoomRateType_success() {
        // 准备参数
        RoomRateTypeCreateReqVO reqVO = randomPojo(RoomRateTypeCreateReqVO.class);

        // 调用
        Long roomRateTypeId = roomRateTypeService.createRoomRateType(reqVO);
        // 断言
        assertNotNull(roomRateTypeId);
        // 校验记录的属性是否正确
        RoomRateTypeDO roomRateType = roomRateTypeMapper.selectById(roomRateTypeId);
        assertPojoEquals(reqVO, roomRateType);
    }

    @Test
    public void testUpdateRoomRateType_success() {
        // mock 数据
        RoomRateTypeDO dbRoomRateType = randomPojo(RoomRateTypeDO.class);
        roomRateTypeMapper.insert(dbRoomRateType);// @Sql: 先插入出一条存在的数据
        // 准备参数
        RoomRateTypeUpdateReqVO reqVO = randomPojo(RoomRateTypeUpdateReqVO.class, o -> {
            o.setId(dbRoomRateType.getId()); // 设置更新的 ID
        });

        // 调用
        roomRateTypeService.updateRoomRateType(reqVO);
        // 校验是否更新正确
        RoomRateTypeDO roomRateType = roomRateTypeMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, roomRateType);
    }

    @Test
    public void testUpdateRoomRateType_notExists() {
        // 准备参数
        RoomRateTypeUpdateReqVO reqVO = randomPojo(RoomRateTypeUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> roomRateTypeService.updateRoomRateType(reqVO), ROOM_RATE_TYPE_NOT_EXISTS);
    }

    @Test
    public void testDeleteRoomRateType_success() {
        // mock 数据
        RoomRateTypeDO dbRoomRateType = randomPojo(RoomRateTypeDO.class);
        roomRateTypeMapper.insert(dbRoomRateType);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbRoomRateType.getId();

        // 调用
        roomRateTypeService.deleteRoomRateType(id);
       // 校验数据不存在了
       assertNull(roomRateTypeMapper.selectById(id));
    }

    @Test
    public void testDeleteRoomRateType_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> roomRateTypeService.deleteRoomRateType(id), ROOM_RATE_TYPE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRoomRateTypePage() {
       // mock 数据
       RoomRateTypeDO dbRoomRateType = randomPojo(RoomRateTypeDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setCreateTime(null);
       });
       roomRateTypeMapper.insert(dbRoomRateType);
       // 测试 name 不匹配
       roomRateTypeMapper.insert(cloneIgnoreId(dbRoomRateType, o -> o.setName(null)));
       // 测试 createTime 不匹配
       roomRateTypeMapper.insert(cloneIgnoreId(dbRoomRateType, o -> o.setCreateTime(null)));
       // 准备参数
       RoomRateTypePageReqVO reqVO = new RoomRateTypePageReqVO();
       reqVO.setName(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<RoomRateTypeDO> pageResult = roomRateTypeService.getRoomRateTypePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbRoomRateType, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRoomRateTypeList() {
       // mock 数据
       RoomRateTypeDO dbRoomRateType = randomPojo(RoomRateTypeDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setCreateTime(null);
       });
       roomRateTypeMapper.insert(dbRoomRateType);
       // 测试 name 不匹配
       roomRateTypeMapper.insert(cloneIgnoreId(dbRoomRateType, o -> o.setName(null)));
       // 测试 createTime 不匹配
       roomRateTypeMapper.insert(cloneIgnoreId(dbRoomRateType, o -> o.setCreateTime(null)));
       // 准备参数
       RoomRateTypeExportReqVO reqVO = new RoomRateTypeExportReqVO();
       reqVO.setName(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<RoomRateTypeDO> list = roomRateTypeService.getRoomRateTypeList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbRoomRateType, list.get(0));
    }

}
