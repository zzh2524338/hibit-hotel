package cn.iocoder.yudao.module.hotel.service.roomtype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.RoomTypeCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.RoomTypeExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.RoomTypePageReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtype.vo.RoomTypeUpdateReqVO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtype.RoomTypeDO;
import cn.iocoder.yudao.module.hotel.dal.mysql.roomtype.RoomTypeMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.List;

import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.buildBetweenTime;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.cloneIgnoreId;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertPojoEquals;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertServiceException;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.randomLongId;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.randomPojo;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.ROOM_TYPE_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;

/**
* {@link RoomTypeServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(RoomTypeServiceImpl.class)
public class RoomTypeServiceImplTest extends BaseDbUnitTest {

    @Resource
    private RoomTypeServiceImpl roomTypeService;

    @Resource
    private RoomTypeMapper roomTypeMapper;

    @Test
    public void testCreateRoomType_success() {
        // 准备参数
        RoomTypeCreateReqVO reqVO = randomPojo(RoomTypeCreateReqVO.class);

        // 调用
        Long roomTypeId = roomTypeService.createRoomType(reqVO);
        // 断言
        assertNotNull(roomTypeId);
        // 校验记录的属性是否正确
        RoomTypeDO roomType = roomTypeMapper.selectById(roomTypeId);
        assertPojoEquals(reqVO, roomType);
    }

    @Test
    public void testUpdateRoomType_success() {
        // mock 数据
        RoomTypeDO dbRoomType = randomPojo(RoomTypeDO.class);
        roomTypeMapper.insert(dbRoomType);// @Sql: 先插入出一条存在的数据
        // 准备参数
        RoomTypeUpdateReqVO reqVO = randomPojo(RoomTypeUpdateReqVO.class, o -> {
            o.setId(dbRoomType.getId()); // 设置更新的 ID
        });

        // 调用
        roomTypeService.updateRoomType(reqVO);
        // 校验是否更新正确
        RoomTypeDO roomType = roomTypeMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, roomType);
    }

    @Test
    public void testUpdateRoomType_notExists() {
        // 准备参数
        RoomTypeUpdateReqVO reqVO = randomPojo(RoomTypeUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> roomTypeService.updateRoomType(reqVO), ROOM_TYPE_NOT_EXISTS);
    }

    @Test
    public void testDeleteRoomType_success() {
        // mock 数据
        RoomTypeDO dbRoomType = randomPojo(RoomTypeDO.class);
        roomTypeMapper.insert(dbRoomType);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbRoomType.getId();

        // 调用
        roomTypeService.deleteRoomType(id);
       // 校验数据不存在了
       assertNull(roomTypeMapper.selectById(id));
    }

    @Test
    public void testDeleteRoomType_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> roomTypeService.deleteRoomType(id), ROOM_TYPE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRoomTypePage() {
       // mock 数据
       RoomTypeDO dbRoomType = randomPojo(RoomTypeDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setCreateTime(null);
       });
       roomTypeMapper.insert(dbRoomType);
       // 测试 name 不匹配
       roomTypeMapper.insert(cloneIgnoreId(dbRoomType, o -> o.setName(null)));
       // 测试 createTime 不匹配
       roomTypeMapper.insert(cloneIgnoreId(dbRoomType, o -> o.setCreateTime(null)));
       // 准备参数
       RoomTypePageReqVO reqVO = new RoomTypePageReqVO();
       reqVO.setName(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<RoomTypeDO> pageResult = roomTypeService.getRoomTypePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbRoomType, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRoomTypeList() {
       // mock 数据
       RoomTypeDO dbRoomType = randomPojo(RoomTypeDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setCreateTime(null);
       });
       roomTypeMapper.insert(dbRoomType);
       // 测试 name 不匹配
       roomTypeMapper.insert(cloneIgnoreId(dbRoomType, o -> o.setName(null)));
       // 测试 createTime 不匹配
       roomTypeMapper.insert(cloneIgnoreId(dbRoomType, o -> o.setCreateTime(null)));
       // 准备参数
       RoomTypeExportReqVO reqVO = new RoomTypeExportReqVO();
       reqVO.setName(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<RoomTypeDO> list = roomTypeService.getRoomTypeList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbRoomType, list.get(0));
    }

}
