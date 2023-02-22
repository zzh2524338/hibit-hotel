package cn.iocoder.yudao.module.hotel.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * Hotel 错误码枚举类
 *
 * member 系统，使用 1-004-000-000 段
 */
public interface ErrorCodeConstants {

    // ========== 公司  1010001000 ============
    ErrorCode COMPANY_NOT_EXISTS = new ErrorCode(1010001000, "公司信息不存在");


    // ========== 分部  1010002000 ============
    ErrorCode BUILDING_NOT_EXISTS = new ErrorCode(1010002000, "公司分部不存在");

    // ========== 房型  1010003000 ============
    ErrorCode ROOM_TYPE_NOT_EXISTS = new ErrorCode(1010003000, "该房型已经下架，请刷新页面重新获取");


    // ========== 房间信息  1010004000 ============
    ErrorCode ROOM_INFO_NOT_EXISTS = new ErrorCode(1010004000, "房间信息不存在");


    // ========== 房价类型  1010005000 ============
    ErrorCode ROOM_RATE_TYPE_NOT_EXISTS = new ErrorCode(1010005000, "房价类型不存在");


    // ========== 房型价格  1010006000 ============
    ErrorCode ROOM_TYPE_RATE_NOT_EXISTS = new ErrorCode(1010006000, "没有查询到当前房型今日的价格,房型为:{}, 请检查是否设置");

    // ========== 会员等级信息  1010007000 ============
    ErrorCode MEMBER_LEVEL_NOT_EXISTS = new ErrorCode(1010007000, "该会员等级不存在");

    // ========== 客人信息  1010008000 ============
    ErrorCode GUEST_INFO_NOT_EXISTS = new ErrorCode(1010008000, "房型价格不存在");
    ErrorCode ID_CARD_INVALID = new ErrorCode(1010008001, "{},的身份证信息输入错误, 请检查:{}");

    // ========== 会员信息  1010009000 ============
    ErrorCode MEMBER_INFO_NOT_EXISTS = new ErrorCode(1010009000, "改会员不存在");

    // ========== 订单信息  1010010000 ============
    ErrorCode ORDER_INFO_NOT_EXISTS = new ErrorCode(1010010000, "订单信息不存在");
    ErrorCode GUEST_INFO_CANNOT_BE_NULL = new ErrorCode(1010010000, "客人信息不能为空");

    // ========== 客史信息 1010011000 ==========
    ErrorCode GUEST_HISTORY_NOT_EXISTS = new ErrorCode(1010011000, "客史信息不存在");


}
