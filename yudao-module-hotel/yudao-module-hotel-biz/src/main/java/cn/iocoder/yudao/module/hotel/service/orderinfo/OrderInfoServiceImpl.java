package cn.iocoder.yudao.module.hotel.service.orderinfo;

import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.IdcardUtil.Idcard;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.module.hotel.controller.admin.order.bo.GuestCheckInInformation;
import cn.iocoder.yudao.module.hotel.controller.admin.order.bo.OrderInfoBaseBO;
import cn.iocoder.yudao.module.hotel.controller.admin.order.bo.OrderInfoBookGuest;
import cn.iocoder.yudao.module.hotel.controller.admin.orderinfo.vo.OrderInfoCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.orderinfo.vo.OrderInfoExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.orderinfo.vo.OrderInfoPageReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.orderinfo.vo.OrderInfoUpdateReqVO;
import cn.iocoder.yudao.module.hotel.convert.guesthistory.GuestHistoryConvert;
import cn.iocoder.yudao.module.hotel.convert.orderinfo.OrderInfoConvert;
import cn.iocoder.yudao.module.hotel.dal.dataobject.folioinfo.FolioInfoDO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.guesthistory.GuestHistoryDO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.orderinfo.OrderInfoDO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomratetype.RoomRateTypeDO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtype.RoomTypeDO;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtyperate.RoomTypeRateDO;
import cn.iocoder.yudao.module.hotel.dal.mysql.folioinfo.FolioInfoMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.guesthistory.GuestHistoryMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.guestinfo.GuestInfoMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.memberinfo.MemberInfoMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.orderinfo.OrderInfoMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.roominfo.RoomInfoMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.roomratetype.RoomRateTypeMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.roomtype.RoomTypeMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.roomtyperate.RoomTypeRateMapper;
import cn.iocoder.yudao.module.hotel.enums.CardTypeEnum;
import cn.iocoder.yudao.module.hotel.enums.OrderStatusEnum;
import cn.iocoder.yudao.module.hotel.service.guesthistory.GuestHistoryService;
import cn.iocoder.yudao.module.hotel.service.guestinfo.GuestInfoService;
import cn.iocoder.yudao.module.hotel.service.memberinfo.MemberInfoService;
import cn.iocoder.yudao.module.hotel.service.memberlevel.MemberLevelService;
import cn.iocoder.yudao.module.hotel.service.roominfo.RoomInfoService;
import cn.iocoder.yudao.module.hotel.service.roomtyperate.RoomTypeRateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hotel.enums.CardTypeEnum.ID_CARD;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.ID_CARD_INVALID;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.ORDER_INFO_NOT_EXISTS;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.ROOM_TYPE_NOT_EXISTS;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.ROOM_TYPE_RATE_NOT_EXISTS;

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
    @Resource
    private FolioInfoMapper folioInfoMapper;


    @Override
    @Transactional
    public Long createOrderInfo(OrderInfoCreateReqVO createReqVO) {
        return null;
        // // 简单校验
        // if (CollectionUtil.isEmpty(createReqVO.getGuestInfos())) {
        //     throw exception(GUEST_INFO_CANNOT_BE_NULL);
        // }
        //
        // // 1. 验证房型信息
        // RoomTypeDO roomTypeDO = roomTypeMapper.selectById(createReqVO.getRoomTypeId());
        // if (roomTypeDO == null || roomTypeDO.getDeleted()) {
        //     log.error("该房型已经下架，请刷新页面重新获取");
        //     throw exception(ROOM_TYPE_NOT_EXISTS);
        // }
        //
        // // 2. 会员信息
        // if (null == createReqVO.getMemberId()) {
        //     log.warn("订单未使用会员, roomType:{},roomNo:{},contactNum:{}", createReqVO.getRoomTypeId(),
        //             createReqVO.getRoomNo(), createReqVO.getContactNumber());
        // } else {
        //     // 查询会员信息
        //     MemberInfoDO memberInfo = memberInfoService.getMemberInfo(createReqVO.getMemberId());
        //     if (memberInfo == null) {
        //         throw exception(MEMBER_INFO_NOT_EXISTS);
        //     }
        //
        //     // 实际花费
        //     BigDecimal actuallyPaid = createReqVO.getActuallyPaid();
        //     memberInfo.setCost(memberInfo.getCost().add(actuallyPaid));
        //     memberInfo.setPoints(memberInfo.getPoints() + actuallyPaid.intValue());
        //
        //     int exp = memberInfo.getExp() + actuallyPaid.intValue();
        //     memberInfo.setExp(exp);
        //
        //     // 查询会员等级
        //     MemberLevelDO memberLevel = memberLevelService.getMemberLevel(memberInfo.getLevelId());
        //     MemberLevelDO nextMemberLevel = memberLevelService.getMemberLevelByLevel(memberLevel.getLevel() + 1);
        //     if (nextMemberLevel == null) {
        //         log.info("该会员已经是最高等级,memberName{},phone:{}", memberInfo.getName(), memberInfo.getPhone());
        //     } else {
        //         if (exp > memberLevel.getEndPoints()) {
        //             // 升级
        //             memberInfo.setLevelId(nextMemberLevel.getId());
        //         }
        //     }
        //
        //     memberInfoMapper.updateById(memberInfo);
        // }
        //
        // // 3. 客人信息
        // Map<String, GuestInfoCreateReqVO> idGuestMap = createReqVO.getGuestInfos()
        //         .stream()
        //         .collect(Collectors.toMap(GuestInfoCreateReqVO::getIdCard, v -> v));
        //
        // List<GuestInfoDO> guestInfoDOS = guestInfoService.getGuestInfoListByIdCards(idGuestMap.keySet());
        // // 已有的客人信息 更新
        // guestInfoDOS.forEach(guestInfoDO -> {
        //     guestInfoDO.setCheckinNum(guestInfoDO.getCheckinNum() + 1);
        //     guestInfoDO.setStayNightNum(guestInfoDO.getStayNightNum() + 1);
        //     guestInfoDO.setLatestCheckin(LocalDateTimeUtil.of(createReqVO.getCheckInTime()));
        //
        //     guestInfoMapper.updateById(guestInfoDO);
        //     // idGuestMap 删除相应的用户
        //     idGuestMap.remove(guestInfoDO.getIdCard());
        // });
        //
        // // 没有的信息需要重新创建
        // idGuestMap.values().forEach(guestInfoService::createGuestInfo);
        //
        // // 4. 房间状态修改
        // RoomInfoDO needUpdateRoom = RoomInfoDO.builder()
        //         .cleanStatus(ORDERED.getValue())
        //         .build();
        //
        // roomInfoMapper.update(needUpdateRoom,
        //         new LambdaQueryWrapper<RoomInfoDO>()
        //                 .eq(RoomInfoDO::getNo, createReqVO.getRoomNo())
        // );
        //
        // // 插入
        // OrderInfoDO orderInfo = OrderInfoConvert.INSTANCE.convert(createReqVO);
        // // 原价从库中查询防止篡改
        // orderInfo.setUuid(IdUtil.getSnowflakeNextIdStr());
        // // orderInfo.setOriginalPrice(roomTypeDO.getPrice());
        // orderInfoMapper.insert(orderInfo);
        // // 返回
        // return orderInfo.getId();
    }


    @Override
    @Transactional
    public Long createOrderInfo(OrderInfoBaseBO req) {
        // 获取房间
        Long roomRateTypeId = req.getRoomRateTypeId();

        RoomRateTypeDO roomRateTypeDO = roomRateTypeMapper.selectById(roomRateTypeId);
        this.processOrderInfoBookGuests(req);

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
            orderAccountSum = this.processRoomRate(
                    req, orderAccountSum, orderInfoBookGuest.getRoomTypeId(), orderInfoBookGuest.getRoomRate());

            this.processGuestInfo(orderInfoBookGuest);
        }

        String orderUuid = IdUtil.getSnowflakeNextIdStr();
        // 保存订单信息
        OrderInfoDO orderInfoDO = OrderInfoConvert.INSTANCE.convert(req);
        orderInfoDO.setUuid(orderUuid)
                .setStatus(OrderStatusEnum.ORDERED.getValue())
                .setOriginalPrice(orderAccountSum)
                .setRoomInfo(JsonUtils.toJsonString(req.getOrderInfoBookGuests()));

        orderInfoMapper.insert(orderInfoDO);

        // 保存房单
        this.saveFolioInfo(req, orderInfoDO.getId());
    }

    /**
     * 处理房价，查询数据库中的信息，并与前端传入的比较
     * 用于计算订单总价
     *
     * @param req
     * @param orderAccountSum
     * @param roomTypeId
     * @param frontendRoomRate
     * @return
     */
    private BigDecimal processRoomRate(OrderInfoBaseBO req, BigDecimal orderAccountSum, Long roomTypeId,
            String frontendRoomRate) {
        // 房间类型
        RoomTypeDO roomTypeDO = roomTypeMapper.selectById(roomTypeId);
        if (roomTypeDO == null) {
            log.error("查询房间类型报错, roomTypeId:{}", roomTypeId);
            throw exception(ROOM_TYPE_NOT_EXISTS);
        }

        // 根据房价类型 和房间类型查询到当前房价
        RoomTypeRateDO roomTypeRateDO = roomTypeRateMapper.selectTodayRateByRoomTypeAndRateType(
                req.getRoomRateTypeId(), roomTypeId);
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
     * 保存房单信息
     */
    private void saveFolioInfo(OrderInfoBaseBO req, Long orderId) {

        List<FolioInfoDO> folioInfoDOS = req.getOrderInfoBookGuests()
                .stream()
                .map(
                        orderInfoBookGuest -> {
                            Long roomId = orderInfoBookGuest.getRoomId();

                            return new FolioInfoDO()
                                    // 如果有id 就说明排房了
                                    .setStatus(roomId == null ? 1 : 2)
                                    .setOrderId(orderId)
                                    .setRoomId(roomId)
                                    .setArrivalTime(orderInfoBookGuest.getArrivalTime())
                                    .setDepartTime(orderInfoBookGuest.getDepartTime())
                                    .setRoomTypeId(orderInfoBookGuest.getRoomTypeId())
                                    .setGuestInfo(JsonUtils.toJsonString(orderInfoBookGuest.getGuestCheckInInfoList()));
                        }
                )
                .collect(Collectors.toList());

        log.info("saveFolioInfos: size:{}", folioInfoDOS.size());

        folioInfoMapper.insertBatch(folioInfoDOS);
        log.info("save folioInfos successfully");
    }

    /**
     * 处理入住客人信息
     * 1. 保存没有创建过的用户到数据库
     *
     * @param orderInfoBookGuest
     */
    private void processGuestInfo(OrderInfoBookGuest orderInfoBookGuest) {
        List<GuestCheckInInformation> guestCheckInInfoList = orderInfoBookGuest.getGuestCheckInInfoList();

        // 根据身份证号 查询到库中的客人信息
        List<String> cartNoList = guestCheckInInfoList.stream()
                .map(GuestCheckInInformation::getCardNo)
                .collect(Collectors.toList());
        List<GuestHistoryDO> guestHistoryDOListInDB = guestHistoryMapper.selectList(GuestHistoryDO::getCardNo,
                cartNoList);

        Set<String> cardNoListInDB = guestHistoryDOListInDB.stream()
                .map(GuestHistoryDO::getCardNo)
                .collect(Collectors.toUnmodifiableSet());

        List<GuestCheckInInformation> guestInfoNotInDB = guestCheckInInfoList.stream()
                .filter(guestInfo -> !cardNoListInDB.contains(guestInfo.getCardNo()))
                .collect(Collectors.toList());

        // 创建客史信息, 并保存到数据库
        List<GuestHistoryDO> guestHistoryDOSNew = this.saveGuestNotInDb(guestInfoNotInDB);

    }


    private List<GuestHistoryDO> saveGuestNotInDb(List<GuestCheckInInformation> guestListNotInDb) {
        List<GuestHistoryDO> guestHistoryDOS = GuestHistoryConvert.INSTANCE.convertList03(guestListNotInDb);
        guestHistoryDOS
                .forEach(guestHistoryDO -> {
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
                });

        guestHistoryMapper.insertBatch(guestHistoryDOS);

        return guestHistoryDOS;
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
