package com.miner.weixin.utils;

import com.miner.weixin.enums.ResultEnum;
import com.miner.weixin.vo.ResultVO;

/**
 * @author 洪峰
 * @create 2018-02-08 20:11
 **/

public class ResultVOUtil {

    public static ResultVO success(Object object){

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMsg(ResultEnum.SUCCESS.getMsg());
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
