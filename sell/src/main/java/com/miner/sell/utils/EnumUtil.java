package com.miner.sell.utils;

import com.miner.sell.enums.CodeEnum;
import com.miner.sell.enums.OrderStatusEnum;

/**
 * @author 洪峰
 * @create 2018-01-23 20:45
 **/

public class EnumUtil {

    public static <T extends CodeEnum>T getByCode(Integer Code,Class<T> enumClass) {
        try{
            for(T t : enumClass.getEnumConstants()){
                if(t.getCode() == Code){
                    return t;
                }
            }
        }catch (Exception e){

        }
        return null;
    }
}
