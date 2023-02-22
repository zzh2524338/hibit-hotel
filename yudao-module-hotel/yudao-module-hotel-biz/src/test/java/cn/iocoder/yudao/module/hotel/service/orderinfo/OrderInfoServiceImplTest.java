package cn.iocoder.yudao.module.hotel.service.orderinfo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.hotel.controller.admin.orderinfo.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.orderinfo.OrderInfoDO;
import cn.iocoder.yudao.module.hotel.dal.mysql.orderinfo.OrderInfoMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.springframework.context.annotation.Import;
import java.util.*;

import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.buildBetweenTime;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static org.junit.jupiter.api.Assertions.*;

/**
* {@link OrderInfoServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(OrderInfoServiceImpl.class)
public class OrderInfoServiceImplTest extends BaseDbUnitTest {

    @Resource
    private OrderInfoServiceImpl orderInfoService;

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Test
    public void testCreateOrderInfo_success() {
        // 准备参数
        OrderInfoCreateReqVO reqVO = randomPojo(OrderInfoCreateReqVO.class);

        // 调用
        Long orderInfoId = orderInfoService.createOrderInfo(reqVO);
        // 断言
        assertNotNull(orderInfoId);
        // 校验记录的属性是否正确
        OrderInfoDO orderInfo = orderInfoMapper.selectById(orderInfoId);
        assertPojoEquals(reqVO, orderInfo);
    }

    @Test
    public void testUpdateOrderInfo_success() {
        // mock 数据
        OrderInfoDO dbOrderInfo = randomPojo(OrderInfoDO.class);
        orderInfoMapper.insert(dbOrderInfo);// @Sql: 先插入出一条存在的数据
        // 准备参数
        OrderInfoUpdateReqVO reqVO = randomPojo(OrderInfoUpdateReqVO.class, o -> {
            o.setId(dbOrderInfo.getId()); // 设置更新的 ID
        });

        // 调用
        orderInfoService.updateOrderInfo(reqVO);
        // 校验是否更新正确
        OrderInfoDO orderInfo = orderInfoMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, orderInfo);
    }

    @Test
    public void testUpdateOrderInfo_notExists() {
        // 准备参数
        OrderInfoUpdateReqVO reqVO = randomPojo(OrderInfoUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> orderInfoService.updateOrderInfo(reqVO), ORDER_INFO_NOT_EXISTS);
    }

    @Test
    public void testDeleteOrderInfo_success() {
        // mock 数据
        OrderInfoDO dbOrderInfo = randomPojo(OrderInfoDO.class);
        orderInfoMapper.insert(dbOrderInfo);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbOrderInfo.getId();

        // 调用
        orderInfoService.deleteOrderInfo(id);
       // 校验数据不存在了
       assertNull(orderInfoMapper.selectById(id));
    }

    @Test
    public void testDeleteOrderInfo_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> orderInfoService.deleteOrderInfo(id), ORDER_INFO_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOrderInfoPage() {
       // mock 数据
       OrderInfoDO dbOrderInfo = randomPojo(OrderInfoDO.class, o -> { // 等会查询到
           o.setStatus(null);
           o.setBookingPerson(null);
           o.setContactName(null);
           o.setContactNumber(null);
           o.setAssure(null);
           o.setAssureTime(null);
           o.setArrivalTime(null);
           o.setDepartTime(null);
           o.setMemberInfo(null);
       });
       orderInfoMapper.insert(dbOrderInfo);
       // 测试 status 不匹配
       orderInfoMapper.insert(cloneIgnoreId(dbOrderInfo, o -> o.setStatus(null)));
       // 测试 bookingPerson 不匹配
       orderInfoMapper.insert(cloneIgnoreId(dbOrderInfo, o -> o.setBookingPerson(null)));
       // 测试 contactName 不匹配
       orderInfoMapper.insert(cloneIgnoreId(dbOrderInfo, o -> o.setContactName(null)));
       // 测试 contactNumber 不匹配
       orderInfoMapper.insert(cloneIgnoreId(dbOrderInfo, o -> o.setContactNumber(null)));
       // 测试 assure 不匹配
       orderInfoMapper.insert(cloneIgnoreId(dbOrderInfo, o -> o.setAssure(null)));
       // 测试 assureTime 不匹配
       orderInfoMapper.insert(cloneIgnoreId(dbOrderInfo, o -> o.setAssureTime(null)));
       // 测试 arrivalTime 不匹配
       orderInfoMapper.insert(cloneIgnoreId(dbOrderInfo, o -> o.setArrivalTime(null)));
       // 测试 departTime 不匹配
       orderInfoMapper.insert(cloneIgnoreId(dbOrderInfo, o -> o.setDepartTime(null)));
       // 测试 memberInfo 不匹配
       orderInfoMapper.insert(cloneIgnoreId(dbOrderInfo, o -> o.setMemberInfo(null)));
       // 准备参数
       OrderInfoPageReqVO reqVO = new OrderInfoPageReqVO();
       reqVO.setStatus(null);
       reqVO.setBookingPerson(null);
       reqVO.setContactName(null);
       reqVO.setContactNumber(null);
       reqVO.setAssure(null);
       reqVO.setAssureTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setArrivalTime(null);
       reqVO.setDepartTime(null);
       reqVO.setMemberInfo(null);

       // 调用
       PageResult<OrderInfoDO> pageResult = orderInfoService.getOrderInfoPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbOrderInfo, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOrderInfoList() {
       // mock 数据
       OrderInfoDO dbOrderInfo = randomPojo(OrderInfoDO.class, o -> { // 等会查询到
           o.setStatus(null);
           o.setBookingPerson(null);
           o.setContactName(null);
           o.setContactNumber(null);
           o.setAssure(null);
           o.setAssureTime(null);
           o.setArrivalTime(null);
           o.setDepartTime(null);
           o.setMemberInfo(null);
       });
       orderInfoMapper.insert(dbOrderInfo);
       // 测试 status 不匹配
       orderInfoMapper.insert(cloneIgnoreId(dbOrderInfo, o -> o.setStatus(null)));
       // 测试 bookingPerson 不匹配
       orderInfoMapper.insert(cloneIgnoreId(dbOrderInfo, o -> o.setBookingPerson(null)));
       // 测试 contactName 不匹配
       orderInfoMapper.insert(cloneIgnoreId(dbOrderInfo, o -> o.setContactName(null)));
       // 测试 contactNumber 不匹配
       orderInfoMapper.insert(cloneIgnoreId(dbOrderInfo, o -> o.setContactNumber(null)));
       // 测试 assure 不匹配
       orderInfoMapper.insert(cloneIgnoreId(dbOrderInfo, o -> o.setAssure(null)));
       // 测试 assureTime 不匹配
       orderInfoMapper.insert(cloneIgnoreId(dbOrderInfo, o -> o.setAssureTime(null)));
       // 测试 arrivalTime 不匹配
       orderInfoMapper.insert(cloneIgnoreId(dbOrderInfo, o -> o.setArrivalTime(null)));
       // 测试 departTime 不匹配
       orderInfoMapper.insert(cloneIgnoreId(dbOrderInfo, o -> o.setDepartTime(null)));
       // 测试 memberInfo 不匹配
       orderInfoMapper.insert(cloneIgnoreId(dbOrderInfo, o -> o.setMemberInfo(null)));
       // 准备参数
       OrderInfoExportReqVO reqVO = new OrderInfoExportReqVO();
       reqVO.setStatus(null);
       reqVO.setBookingPerson(null);
       reqVO.setContactName(null);
       reqVO.setContactNumber(null);
       reqVO.setAssure(null);
       reqVO.setAssureTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setArrivalTime(null);
       reqVO.setDepartTime(null);
       reqVO.setMemberInfo(null);

       // 调用
       List<OrderInfoDO> list = orderInfoService.getOrderInfoList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbOrderInfo, list.get(0));
    }

}
