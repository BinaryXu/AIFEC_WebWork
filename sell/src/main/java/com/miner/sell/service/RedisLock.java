package com.miner.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author 洪峰
 * @create 2018-02-03 11:22
 **/
@Component
@Slf4j
public class RedisLock {

    @Autowired
    StringRedisTemplate redisTemplate;

    //加锁
    public boolean lock(String key,String value){
       boolean result = redisTemplate.opsForValue().setIfAbsent(key, value);
       if(result){
           return true;
       }
        /**
         * 该方法1.防止加锁后，后续代码可能会出现异常导致不能正常解锁，从而形成死锁。
         * 首先第一次请求定义value为当前时间+超时时间
         *
         * 第二次和第三次为两个线程同时请求
         *         key        value      currentValue  当前时间    oldValue    使用GETSET方法后的value
         *
         * 第一次   abc      100+10=110        110         100
         * 第二次   abc      200+10=220        110         130       110              220
         * 第三次   abc      200+10=220        110         130       220              220
         *
         *注：redis为单线程模式，所以第二次和第三次只能有一个请求进入getAndSet方法
         *
         */
       //获取该Key的value
       String currentValue = redisTemplate.opsForValue().get(key);
       if(!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue)< System.currentTimeMillis()){
           //获取上一个value
           String oldValue = redisTemplate.opsForValue().getAndSet(key,value);
           if(!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)){
               return true;
           }
       }
       return false;
    }

    //解锁
    public void unlock(String key,String value){
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            log.error("【redis分布式锁】解锁异常,{}",e);
        }
    }
}
