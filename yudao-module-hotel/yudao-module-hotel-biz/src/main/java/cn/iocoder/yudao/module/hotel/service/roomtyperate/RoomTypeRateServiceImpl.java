package cn.iocoder.yudao.module.hotel.service.roomtyperate;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.date.Week;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.SetUtils;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.bo.RoomRateCreateReqBO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo.RoomTypeRateCreateReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo.RoomTypeRateExportReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo.RoomTypeRateListRespVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo.RoomTypeRatePageReqVO;
import cn.iocoder.yudao.module.hotel.controller.admin.roomtyperate.vo.RoomTypeRateUpdateReqVO;
import cn.iocoder.yudao.module.hotel.convert.roomtyperate.RoomTypeRateConvert;
import cn.iocoder.yudao.module.hotel.dal.dataobject.roomtyperate.RoomTypeRateDO;
import cn.iocoder.yudao.module.hotel.dal.mysql.roomtype.RoomTypeMapper;
import cn.iocoder.yudao.module.hotel.dal.mysql.roomtyperate.RoomTypeRateMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.hotel.enums.ErrorCodeConstants.ROOM_TYPE_RATE_NOT_EXISTS;

/**
 * 房型价格 Service 实现类
 *
 * @author 芋道源码
 */
@Slf4j
@Service
@Validated
public class RoomTypeRateServiceImpl implements RoomTypeRateService {

    @Resource
    private RoomTypeRateMapper roomTypeRateMapper;
    @Resource
    private RoomTypeMapper roomTypeMapper;

    @Override
    public Long createRoomTypeRate(RoomTypeRateCreateReqVO createReqVO) {
        // 插入
        RoomTypeRateDO roomTypeRate = RoomTypeRateConvert.INSTANCE.convert(createReqVO);
        roomTypeRateMapper.insert(roomTypeRate);
        // 返回
        return roomTypeRate.getId();
    }

    @Override
    public Long createRoomTypeRate(RoomRateCreateReqBO req) {
        log.info("createRoomTypeRate:{}", req);

        Set<Week> weeks = SetUtils.asSet(req.getWeekDay());

        List<RoomTypeRateDO> roomTypeRateDOInserts = new ArrayList<>();
        List<RoomTypeRateDO> roomTypeRateDOUpdates = new ArrayList<>();
        for (LocalDate start = req.getStartDate(); !start.isAfter(req.getEndDate()); start = start.plusDays(1)) {
            for (RoomRateCreateReqBO.RoomRate roomRate : req.getRoomRateList()) {
                LocalDateTime startDateTime = LocalDateTimeUtil.of(start);
                RoomTypeRateDO roomTypeRateDO = new RoomTypeRateDO()
                        .setRoomRateTypeId(roomRate.getRoomRateTypeId())
                        .setRoomTypeId(roomRate.getRoomTypeId())
                        .setAccDate(startDateTime);
                // 今天是否要使用周末价格
                if (weeks.contains(LocalDateTimeUtil.dayOfWeek(start))) {
                    roomTypeRateDO.setRoomRate(new BigDecimal(roomRate.getWeekRate()));
                } else {
                    roomTypeRateDO.setRoomRate(new BigDecimal(roomRate.getRate()));
                }

                // 查询表中是否已经有了当天数据，有的话更新，没有新增
                RoomTypeRateDO roomTypeRateInDB = roomTypeRateMapper.selectOneByTypeIdAndAccDate(
                        roomRate.getRoomRateTypeId(), roomRate.getRoomTypeId(), startDateTime);
                if (roomTypeRateInDB != null) {
                    log.info("当天已有数据, 进行更新操作 roomTypeRateInDB:{}", roomTypeRateInDB);
                    roomTypeRateDO.setId(roomTypeRateInDB.getId());

                    roomTypeRateDOUpdates.add(roomTypeRateDO);
                } else {
                    roomTypeRateDOInserts.add(roomTypeRateDO);
                }
            }

            roomTypeRateMapper.insertBatch(roomTypeRateDOInserts);
            roomTypeRateMapper.updateBatch(roomTypeRateDOUpdates, 1000);

            log.info("createRoomTypeRate success");
        }

        return null;
    }

    @Override
    public void updateRoomTypeRate(RoomTypeRateUpdateReqVO updateReqVO) {
        // 校验存在
        validateRoomTypeRateExists(updateReqVO.getId());
        // 更新
        RoomTypeRateDO updateObj = RoomTypeRateConvert.INSTANCE.convert(updateReqVO);
        roomTypeRateMapper.updateById(updateObj);
    }

    @Override
    public void deleteRoomTypeRate(Long id) {
        // 校验存在
        validateRoomTypeRateExists(id);
        // 删除
        roomTypeRateMapper.deleteById(id);
    }

    private void validateRoomTypeRateExists(Long id) {
        if (roomTypeRateMapper.selectById(id) == null) {
            log.error("查询房型价格报错，房间类型不存在，roomTypeRateId:{}", id);
            throw exception(ROOM_TYPE_RATE_NOT_EXISTS);
        }
    }

    @Override
    public RoomTypeRateDO getRoomTypeRate(Long id) {
        return roomTypeRateMapper.selectById(id);
    }

    @Override
    public List<RoomTypeRateDO> getRoomTypeRateList(Collection<Long> ids) {
        return roomTypeRateMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<RoomTypeRateDO> getRoomTypeRatePage(RoomTypeRatePageReqVO pageReqVO) {
        return roomTypeRateMapper.selectPage(pageReqVO);
    }

    @Override
    public ArrayList<RoomTypeRateListRespVO> getRoomTypeRatePageByDate(RoomTypeRateExportReqVO pageReqVO) {
        // 先查询房间类型
        List<RoomTypeRateDO> roomTypeRateDOS = roomTypeRateMapper.selectList(pageReqVO);

        // 1.根据时间划分
        Map<LocalDateTime, List<RoomTypeRateDO>> localDateTimeListMap = roomTypeRateDOS.stream()
                .collect(Collectors.groupingBy(RoomTypeRateDO::getAccDate));

        ArrayList<RoomTypeRateListRespVO> roomTypeRateListRespVOS = new ArrayList<>();
        for (Map.Entry<LocalDateTime, List<RoomTypeRateDO>> localDateTimeListEntry : localDateTimeListMap.entrySet()) {
            // 房间类型和每个房型的各种价格map
            List<RoomTypeRateListRespVO.RoomTypeAndRate> roomTypeAndRates = localDateTimeListEntry.getValue()
                    .stream()
                    .collect(Collectors.groupingBy(RoomTypeRateDO::getRoomTypeId))
                    .entrySet()
                    .stream()
                    .map(longListEntry -> new RoomTypeRateListRespVO.RoomTypeAndRate()
                            .setRoomTypeId(longListEntry.getKey())
                            .setRoomTypeRateDOList(longListEntry.getValue()))
                    .collect(Collectors.toList());

            roomTypeRateListRespVOS.add(
                    new RoomTypeRateListRespVO()
                            .setAccDate(LocalDateTimeUtil.formatNormal(localDateTimeListEntry.getKey()))
                            .setRoomTypeAndRates(roomTypeAndRates)
            );
        }

        log.info("localDateTimeListMap:{}", roomTypeRateListRespVOS);
        return roomTypeRateListRespVOS;
    }

    @Override
    public List<RoomTypeRateListRespVO> getRoomTypeRatePageByDate() {
        LocalDateTime[] dateTimes = {LocalDateTimeUtil.beginOfDay(LocalDateTime.now()),
                LocalDateTimeUtil.endOfDay(LocalDateTime.now())};

        List<RoomTypeRateListRespVO> map = this.getRoomTypeRatePageByDate(
                new RoomTypeRateExportReqVO()
                        .setAccDate(dateTimes)
        );
        return map;
    }

    @Override
    public List<RoomTypeRateDO> getRoomTypeRateList(RoomTypeRateExportReqVO exportReqVO) {
        return roomTypeRateMapper.selectList(exportReqVO);
    }

}
