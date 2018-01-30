package com.miner.sell.aspect;

import com.miner.sell.constant.CookieConstant;
import com.miner.sell.constant.RedisConstant;
import com.miner.sell.exception.SellerAuthorizeException;
import com.miner.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * 通过AOP实现登录验证
 * @author 洪峰
 * @create 2018-01-30 19:57
 **/
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.miner.sell.controller.Sell*.*(..))" +
            "&& !execution(public * com.miner.sell.controller.SellerInfoController.*(..))")
    public void verify(){}

    @Before("verify()")
    public void doVerify(){

        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //查询Cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie == null){
            log.warn("【登录验证】Cookie查询不到token！！！");
            throw new SellerAuthorizeException();
        }
        //查询Redis
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
        if(tokenValue == null){
            log.warn("【登录验证】Redis查询不到token！！！");
            throw new SellerAuthorizeException();
        }
    }

}
