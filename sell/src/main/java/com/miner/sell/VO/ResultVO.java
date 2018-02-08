package com.miner.sell.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 洪峰
 * @create 2018-01-19 22:40
 **/
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -613519204975060172L;
    private Integer code;

    private String msg;

    private T data;
}
