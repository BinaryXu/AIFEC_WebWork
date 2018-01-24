package com.miner.sell.utils;

import java.util.Random;

/**
 * @author 洪峰
 * @create 2018-01-20 15:53
 **/

public class KeyUtil {

    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }

}
