package cn.iocoder.yudao.module.hotel.service.order;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.IdcardUtil.Idcard;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.hotel.controller.admin.guestinfo.vo.GuestInfoCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.order.bo.GuestCheckInInformation;
import cn.iocoder.yudao.module.hotel.controller.admin.order.bo.OrderInfoBaseBO;
import cn.iocoder.yudao.module.hotel.controller.admin.order.bo.OrderInfoBookGuest;
import cn.iocoder.yudao.module.hotel.controller.admin.order.vo.OrderInfoCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.order.vo.OrderInfoExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.order.vo.OrderInfoPageReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.order.vo.OrderInfoUpdateReqVO;
import cn.iocoder.yudao.module.hotel.convert.order.OrderInfoConvert;
import cn.iocoder.yudao.module.hotel.dal.dataobject.guesthistory.GuestHistoryDO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.guestinfo.GuestInfoDO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberinfo.MemberInfoDO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.memberlevel.MemberLevelDO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.order.OrderInfoDO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roominfo.RoomInfoDO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomratetype.RoomRateTypeDO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtype.RoomTypeDO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtyperate.RoomTypeRateDO;
import cn.iocoder.yudao.module.hotel.dal.mysql.guesthistory.GuestHistoryMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.guestinfo.GuestInfoMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.memberinfo.MemberInfoMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.order.OrderInfoMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.roominfo.RoomInfoMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.roomratetype.RoomRateTypeMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.roomtype.RoomTypeMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.roomtyperate.RoomTypeRateMapper;
import cn.iocoder.yudao.module.hotel.enums.CardTypeEnum;
import cn.iocoder.yudao.module.hotel.service.guesthistory.GuestHistoryService;
import cn.iocoder.yudao.module.hotel.service.guestinfo.GuestInfoService;
import cn.iocoder.yudao.module.hotel.service.memberinfo.MemberInfoService;
import cn.iocoder.yudao.module.hotel.service.memberlevel.MemberLevelService;
import cn.iocoder.yudao.module.hotel.service.roominfo.RoomInfoService;
import cn.iocoder.yudao.module.hotel.service.roomtyperate.RoomTypeRateService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hotel.enums.CardTypeEnum.ID_CARD;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.GUEST_INFO_CANNOT_BE_NULL;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.ID_CARD_INVALID;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.MEMBER_INFO_NOT_EXISTS;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.ORDER_INFO_NOT_EXISTS;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.ROOM_TYPE_NOT_EXISTS;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.ROOM_TYPE_RATE_NOT_EXISTS;
import static cn.iocoder.yudao.module.hotel.enums.RoomStatusEnum.ORDERED;

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
    @Resource
    private RoomRateTypeMapper roomRateTypeMapper;
    @Resource
    private RoomTypeRateMapper roomTypeRateMapper;
    @Resource
    private RoomTypeRateService roomTypeRateService;
    @Resource
    private GuestHistoryService guestHistoryService;
    @Resource
    private GuestHistoryMapper guestHistoryMapper;


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

            guestInfoMapper.updateById(guestInfoDO);
            // idGuestMap 删除相应的用户
            idGuestMap.remove(guestInfoDO.getIdCard());
        });

        // 没有的信息需要重新创建
        idGuestMap.values().forEach(guestInfoService::createGuestInfo);

        // 4. 房间状态修改
        RoomInfoDO needUpdateRoom = RoomInfoDO.builder()
                .cleanStatus(ORDERED.getValue())
                .build();

        roomInfoMapper.update(needUpdateRoom,
                new LambdaQueryWrapper<RoomInfoDO>()
                        .eq(RoomInfoDO::getNo, createReqVO.getRoomNo())
        );

        // 插入
        OrderInfoDO orderInfo = OrderInfoConvert.INSTANCE.convert(createReqVO);
        // 原价从库中查询防止篡改
        orderInfo.setUuid(IdUtil.getSnowflakeNextIdStr());
        // orderInfo.setOriginalPrice(roomTypeDO.getPrice());
        orderInfoMapper.insert(orderInfo);
        // 返回
        return orderInfo.getId();
    }


    @Override
    @Transactional
    public Long createOrderInfo(OrderInfoBaseBO req) {
        // 获取房间
        Long roomRateTypeId = req.getRoomRateTypeId();

        RoomRateTypeDO roomRateTypeDO = roomRateTypeMapper.selectById(roomRateTypeId);

        return null;
    }

    /**
     * 操作步骤
     * 1. 根据每个房间查询到每个房间的房价 并对比，以数据库中的为准
     *
     * @param req
     */
    private void processOrderInfoBookGuests(OrderInfoBaseBO req) {
        // 根据房价类型查询到房间价格
        RoomRateTypeDO roomRateTypeDO = roomRateTypeMapper.selectById(req.getRoomRateTypeId());

        List<OrderInfoBookGuest> orderInfoBookGuests = req.getOrderInfoBookGuests();

        // 订单总价
        BigDecimal orderAccountSum = new BigDecimal(0);
        for (OrderInfoBookGuest orderInfoBookGuest : orderInfoBookGuests) {
            orderAccountSum = processRoomRate(
                    req, orderAccountSum, orderInfoBookGuest.getRoomTypeId(), orderInfoBookGuest.getRoomRate());

            this.processGuestInfo(orderInfoBookGuest);
        }
    }

    private BigDecimal processRoomRate(OrderInfoBaseBO req, BigDecimal orderAccountSum, Long roomTypeId,
            String frontendRoomRate) {
        // 房间类型
        RoomTypeDO roomTypeDO = roomTypeMapper.selectById(roomTypeId);
        if (roomTypeDO == null) {
            log.error("查询房间类型报错, roomTypeId:{}", roomTypeId);
            throw exception(ROOM_TYPE_NOT_EXISTS);
        }

        // 根据房价类型 和房间类型查询到当前房价
        RoomTypeRateDO roomTypeRateDO = roomTypeRateMapper.selectOneByTypeIdAndAccDate(
                req.getRoomRateTypeId(), roomTypeId, LocalDateTime.now());
        if (roomTypeRateDO == null) {
            log.error("没有查询到当前房型的价格，roomTypeRateId:{},roomTypeId:{}, dateTime:{}",
                    req.getRoomRateTypeId(), roomTypeId, LocalDateTime.now());
            throw exception(ROOM_TYPE_RATE_NOT_EXISTS, roomTypeDO.getName());
        }

        BigDecimal roomRate = roomTypeRateDO.getRoomRate();
        BigDecimal frontendRate = new BigDecimal(frontendRoomRate);
        if (0 != roomRate.compareTo(frontendRate)) {
            log.error("数据库中的价格与前端传入的价格不等，以数据库中的为主。db:{}, frontend:{}", roomRate, frontendRate);
        }

        orderAccountSum = orderAccountSum.add(roomRate);
        return orderAccountSum;
    }

    /**
     * 处理入住客人信息
     *
     * @param orderInfoBookGuest
     */
    private void processGuestInfo(OrderInfoBookGuest orderInfoBookGuest) {
        List<GuestCheckInInformation> guestCheckInInfoList = orderInfoBookGuest.getGuestCheckInInfoList();

        // 根据身份证号 查询到库中的客人信息
        List<String> guestIds = guestCheckInInfoList.stream()
                .map(GuestCheckInInformation::getGuestId)
                .collect(Collectors.toList());

        List<GuestHistoryDO> guestHistoryDOS = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(guestIds)) {
            guestHistoryDOS = guestHistoryMapper.selectList(GuestHistoryDO::getId, guestIds);
        }

        for (GuestHistoryDO guestHistoryDO : guestHistoryDOS) {
            // 如果是身份证，要输入有效身份证以便解析相关字段
            CardTypeEnum cardTypeEnum = EnumUtil.getBy(CardTypeEnum::getValue, guestHistoryDO.getCardType());
            String cardNo = guestHistoryDO.getCardNo();
            String guestName = guestHistoryDO.getGuestName();
            if (cardTypeEnum == ID_CARD) {
                if (!IdcardUtil.isValidCard(cardNo)) {
                    log.error("身份证信息输入错误，guestName:{}, cardNo:{}", guestName, cardNo);
                    throw exception(ID_CARD_INVALID, guestName, cardNo);
                }

                this.parseIdCard(cardNo, guestHistoryDO);
            }
        }

        for (GuestHistoryDO guestHistoryDO : guestHistoryDOS) {
            guestHistoryDO.setLatestCheckin(orderInfoBookGuest.getArrivalTime());
        }

    }

    /**
     * 根据身份证信息获取 地址性别，生日信息
     *
     * @param cardNo 证件号
     * @param guestHistoryDO 数据库对象
     */
    private void parseIdCard(String cardNo, GuestHistoryDO guestHistoryDO) {
        if (StringUtils.isBlank(cardNo) || !IdcardUtil.isValidCard(cardNo)) {
            return;
        }
        Idcard idCardInfo = IdcardUtil.getIdcardInfo(cardNo);

        guestHistoryDO.setBirthday(idCardInfo.getBirthDate().toLocalDateTime().toLocalDate());
        guestHistoryDO.setGender(idCardInfo.getGender());
        guestHistoryDO.setAddress(idCardInfo.getProvince() + idCardInfo.getCityCode());
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
