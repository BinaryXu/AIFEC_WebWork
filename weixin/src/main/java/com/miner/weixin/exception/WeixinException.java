package com.miner.weixin.exception;

import com.miner.weixin.enums.ResultEnum;
import lombok.Data;

/**
 * @author 洪峰
 * @create 2018-02-04 15:03
 **/
@Data
public class WeixinException extends RuntimeException {

    private Integer code;

    public WeixinException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public WeixinException(Integer code,String msg){
        super(msg);
        this.code = code;
    }
}
