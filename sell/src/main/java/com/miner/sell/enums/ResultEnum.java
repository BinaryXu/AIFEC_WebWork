package com.miner.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(0000,"成功"),

    PRODUCT_NOT_EXIT(10,"查询该商品为空！"),

    PRODUCT_STOCK_ERROR(11,"库存不足"),

    ORDER_NOT_EXIT(12,"订单不存在"),

    ORDER_STATUS_ERROR(13,"订单状态不正确"),

    ORDER_DETAIL_NULL(14,"订单详情为空"),

    ORDER_UPDATE_ERROR(15,"订单更新失败"),

    ORDER_PAY_STATUS_ERROR(16,"支付状态不正确"),

    REQUEST_PARAM_ERROR(17,"请求参数不正确"),

    REQUEST_CART_ERROR(18,"订单购物车为空"),

    REQUEST_WEIID_ERROR(19,"微信ID不能为空"),

    ORDER_NOT_USER(20,"根据该用户查不到该订单号"),

    ORDER_CANCEL_SUCCESS(21,"订单取消成功"),

    ORDER_FINISH_SUCCESS(22,"订单完结成功"),

    PRODUCT_STATUS_ERROR(23,"商品状态不正确"),

    CATEGORY_NOT_EXIT(24,"商品类目不存在"),

    CATEGORY_ERROR(24,"商品类目操作失败")

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
