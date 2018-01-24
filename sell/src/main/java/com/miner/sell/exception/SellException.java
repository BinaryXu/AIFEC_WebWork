package com.miner.sell.exception;

import com.miner.sell.enums.ResultEnum;
import lombok.Data;

/**
 * 项目统一错误处理类
 * @author 洪峰
 * @create 2018-01-20 15:42
 **/
@Data
public class SellException extends RuntimeException {

    private Integer code;

    public SellException (ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
