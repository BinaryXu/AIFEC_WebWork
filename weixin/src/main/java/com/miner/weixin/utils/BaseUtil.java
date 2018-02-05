package com.miner.weixin.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

/**
 * @author 洪峰
 * @create 2018-02-05 19:47
 **/
@Slf4j
public class BaseUtil {

    /**
     * 获取UUID
     * @return
     */
    public synchronized static String getUUID(){
        Random random = new Random();

        return UUID.randomUUID().toString()+String.valueOf(random.nextInt(90000)+10000);
    }

}
