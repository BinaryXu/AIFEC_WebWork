package com.miner.sell.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum implements CodeEnum{

    FAIL(0,"待支付"),
    SUCCESS(1,"已支付");

    private Integer code;

    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
