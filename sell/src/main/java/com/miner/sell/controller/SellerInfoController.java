package com.miner.sell.controller;

import com.miner.sell.config.ProjectUrlConfig;
import com.miner.sell.constant.CookieConstant;
import com.miner.sell.constant.RedisConstant;
import com.miner.sell.dataobject.SellerInfo;
import com.miner.sell.enums.ResultEnum;
import com.miner.sell.service.SellerInfoService;
import com.miner.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 卖家信息登录登出
 * @author 洪峰
 * @create 2018-01-29 21:12
 **/

@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerInfoController {

    @Autowired
    private SellerInfoService sellerInfoService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //登录
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openId") String openId,
                              Map<String,Object> map,
                              HttpServletResponse response){
        //判断数据库是否存在
        SellerInfo sellerInfo = sellerInfoService.findByOpenId(openId);
        if(sellerInfo == null){
            log.error("【登录】录入openID不正确={}",openId);
            map.put("msg", ResultEnum.CATEGORY_NOT_EXIT.getMessage());
            map.put("url","/sell/list/");
            return  new ModelAndView("common/error",map);
        }

        //设置token到redis中
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;

        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openId,expire, TimeUnit.SECONDS);

        //设置token到Cookie中
        CookieUtil.set(response, CookieConstant.TOKEN,token,expire);

        return new ModelAndView("redirect:"+projectUrlConfig.getSell()+"/sell/order/list");

    }

    //登出
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String,Object> map){
        //查询Cookie
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
        if(cookie != null) {
            //清除Redis缓存
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));

            //清除Cookie
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        }
        map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url","/sell/sell/order/list");
        return new ModelAndView("common/success",map);
    }
}
