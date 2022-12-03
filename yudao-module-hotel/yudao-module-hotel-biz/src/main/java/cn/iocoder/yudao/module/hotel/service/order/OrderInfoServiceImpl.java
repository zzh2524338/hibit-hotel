package cn.iocoder.yudao.module.hotel.service.order;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo.GuestInfoCreateReqVO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.guestinfo.GuestInfoDO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberinfo.MemberInfoDO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberlevel.MemberLevelDO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roominfo.RoomInfoDO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtype.RoomTypeDO;
import cn.iocoder.yudao.module.hotel.dal.mysql.guestinfo.GuestInfoMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.memberinfo.MemberInfoMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.roominfo.RoomInfoMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.roomtype.RoomTypeMapper;
import cn.iocoder.yudao.module.hotel.service.guestinfo.GuestInfoService;
import cn.iocoder.yudao.module.hotel.service.memberinfo.MemberInfoService;
import cn.iocoder.yudao.module.hotel.service.memberlevel.MemberLevelService;
import cn.iocoder.yudao.module.hotel.service.roominfo.RoomInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.hotel.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.hotel.dal.dataobject.order.OrderInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.hotel.convert.order.OrderInfoConvert;
import cn.iocoder.yudao.module.hotel.dal.mysql.order.OrderInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.*;

/**
 * 订单 Service 实现类
 *
 * @author 芋道源码
 */
@Slf4j
@Service
@Validated
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private RoomTypeMapper roomTypeMapper;
    @Resource
    private MemberInfoService memberInfoService;
    @Resource
    private MemberInfoMapper memberInfoMapper;
    @Resource
    private MemberLevelService memberLevelService;
    @Resource
    private GuestInfoService guestInfoService;
    @Resource
    private GuestInfoMapper guestInfoMapper;
    @Resource
    private RoomInfoService roomInfoService;
    @Resource
    private RoomInfoMapper roomInfoMapper;

    @Override
    @Transactional
    public Long createOrderInfo(OrderInfoCreateReqVO createReqVO) {
        // 简单校验
        if (CollectionUtil.isEmpty(createReqVO.getGuestInfos())) {
            throw exception(GUEST_INFO_CANNOT_BE_NULL);
        }

        // 1. 验证房型信息
        RoomTypeDO roomTypeDO = roomTypeMapper.selectById(createReqVO.getRoomTypeId());
        if (roomTypeDO == null || roomTypeDO.getDeleted()) {
            log.error("该房型已经下架，请刷新页面重新获取");
            throw exception(ROOM_TYPE_NOT_EXISTS);
        }

        // 2. 会员信息
        if (null == createReqVO.getMemberId()) {
            log.warn("订单未使用会员, roomType:{},roomNo:{},contactNum:{}", createReqVO.getRoomTypeId(),
                    createReqVO.getRoomNo(), createReqVO.getContactNumber());
        } else {
            // 查询会员信息
            MemberInfoDO memberInfo = memberInfoService.getMemberInfo(createReqVO.getMemberId());
            if (memberInfo == null) {
                throw exception(MEMBER_INFO_NOT_EXISTS);
            }

            // 实际花费
            BigDecimal actuallyPaid = createReqVO.getActuallyPaid();
            memberInfo.setCost(memberInfo.getCost().add(actuallyPaid));
            memberInfo.setPoints(memberInfo.getPoints() + actuallyPaid.intValue());

            int exp = memberInfo.getExp() + actuallyPaid.intValue();
            memberInfo.setExp(exp);

            // 查询会员等级
            MemberLevelDO memberLevel = memberLevelService.getMemberLevel(memberInfo.getLevelId());
            MemberLevelDO nextMemberLevel = memberLevelService.getMemberLevelByLevel(memberLevel.getLevel() + 1);
            if (nextMemberLevel == null) {
                log.info("该会员已经是最高等级,memberName{},phone:{}", memberInfo.getName(), memberInfo.getPhone());
            } else {
                if (exp > memberLevel.getEndPoints()) {
                    // 升级
                    memberInfo.setLevelId(nextMemberLevel.getId());
                }
            }

            memberInfoMapper.updateById(memberInfo);
        }

        // 3. 客人信息
        Map<String, GuestInfoCreateReqVO> idGuestMap = createReqVO.getGuestInfos()
                .stream()
                .collect(Collectors.toMap(GuestInfoCreateReqVO::getIdCard, v -> v));

        List<GuestInfoDO> guestInfoDOS = guestInfoService.getGuestInfoListByIdCards(idGuestMap.keySet());
        // 已有的客人信息 更新
        guestInfoDOS.forEach(guestInfoDO -> {
            guestInfoDO.setCheckinNum(guestInfoDO.getCheckinNum() + 1);
            guestInfoDO.setStayNightNum(guestInfoDO.getStayNightNum() + 1);
            guestInfoDO.setLatestCheckin(LocalDateTimeUtil.of(createReqVO.getCheckInTime()));

            // idGuestMap 删除相应的用户
            idGuestMap.remove(guestInfoDO.getIdCard());
        });
        guestInfoService.updateBatchById(guestInfoDOS);

        // 没有的信息需要重新创建
        idGuestMap.values().forEach(guestInfoService::createGuestInfo);


        // 4. 房间状态修改
         RoomInfoDO.builder()
                 .status()
                         .build()
        roomInfoMapper.update()

        // 插入
        OrderInfoDO orderInfo = OrderInfoConvert.INSTANCE.convert(createReqVO);
        orderInfoMapper.insert(orderInfo);
        // 返回
        return orderInfo.getId();
    }

    @Override
    public void updateOrderInfo(OrderInfoUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateOrderInfoExists(updateReqVO.getId());
        // 更新
        OrderInfoDO updateObj = OrderInfoConvert.INSTANCE.convert(updateReqVO);
        orderInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderInfo(Long id) {
        // 校验存在
        this.validateOrderInfoExists(id);
        // 删除
        orderInfoMapper.deleteById(id);
    }

    private void validateOrderInfoExists(Long id) {
        if (orderInfoMapper.selectById(id) == null) {
            throw exception(ORDER_INFO_NOT_EXISTS);
        }
    }

    @Override
    public OrderInfoDO getOrderInfo(Long id) {
        return orderInfoMapper.selectById(id);
    }

    @Override
    public List<OrderInfoDO> getOrderInfoList(Collection<Long> ids) {
        return orderInfoMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<OrderInfoDO> getOrderInfoPage(OrderInfoPageReqVO pageReqVO) {
        return orderInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OrderInfoDO> getOrderInfoList(OrderInfoExportReqVO exportReqVO) {
        return orderInfoMapper.selectList(exportReqVO);
    }

}
