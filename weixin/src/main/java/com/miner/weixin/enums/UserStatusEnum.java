package com.miner.weixin.enums;

import lombok.Getter;

@Getter
public enum UserStatusEnum {
    NORMAL(0,"正常状态"),
    INVALID(1,"失效状态")
    ;

    private Integer code;

    private String msg;

    UserStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
