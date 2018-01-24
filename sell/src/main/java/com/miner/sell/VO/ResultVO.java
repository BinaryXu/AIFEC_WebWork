package com.miner.sell.VO;

import lombok.Data;

/**
 * @author 洪峰
 * @create 2018-01-19 22:40
 **/
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
