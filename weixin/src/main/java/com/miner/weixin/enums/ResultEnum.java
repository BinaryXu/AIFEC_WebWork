package com.miner.weixin.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    VALID_ERROR(1003,"验证失败"),
    APPID_ERROR(1004,"APPID参数不正确"),
    QRCODE_ERROR(1005,"二维码生成异常"),
    UUID_NULL(1006,"UUID参数不能为空"),
    QRCODE_OVER(1007,"二维码已过期"),
    CODE_OVER(1008,"code已过期")
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
