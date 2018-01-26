package com.miner.sell.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum implements CodeEnum{

    UP(0,"在售"),
    DOWN(1,"下架")
    ;


    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
