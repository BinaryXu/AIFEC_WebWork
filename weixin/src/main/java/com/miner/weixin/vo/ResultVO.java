package com.miner.weixin.vo;

import lombok.Data;

/**
 * @author 洪峰
 * @create 2018-02-08 20:12
 **/
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
