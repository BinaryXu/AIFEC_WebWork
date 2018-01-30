package com.miner.sell.handle;

import com.miner.sell.VO.ResultVO;
import com.miner.sell.exception.SellException;
import com.miner.sell.exception.SellerAuthorizeException;
import com.miner.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 洪峰
 * @create 2018-01-21 13:49
 **/
@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handle(Exception e){
        if(e instanceof SellException) {
            SellException sellException = (SellException) e;
            return ResultVOUtil.error(sellException.getCode(), sellException.getMessage());
        }else if (e instanceof SellerAuthorizeException){
            return new ModelAndView("redirect:www.baidu.com");

        }else{
            log.error("【系统异常】={}",e);
            return ResultVOUtil.error(-1,"未知错误！！！");
        }
    }

}
