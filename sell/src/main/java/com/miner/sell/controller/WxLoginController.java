package com.miner.sell.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.miner.sell.VO.ResAccessTokenVO;
import com.miner.sell.config.ProjectUrlConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 洪峰
 * @create 2018-02-08 21:29
 **/
@RestController
@RequestMapping("/wx")
@Slf4j
public class WxLoginController {

    @Autowired
    ProjectUrlConfig projectUrlConfig;

    private String appid = "wx1185068965";

    @GetMapping("/login")
    public ModelAndView login(){

        String url = projectUrlConfig.getWxUrl()+"/connect/qrconnect?appid="+appid+"&redirect_uri="+projectUrlConfig.getSell()+"/wx/code&response_type=code&scope=snsapi_login&state=3d6be0a4035d839573b04816624a415e#wechat_redirect";
        return new ModelAndView("redirect:"+url);

    }
    @GetMapping("/code")
    public void code(@RequestParam("code")String code){

        String url = projectUrlConfig.getWxUrl()+"/sns/oauth2/access_token?appid="+appid+"&secret=SECRET&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String resp = restTemplate.getForObject(url,String.class);
        log.info(resp);
        ResAccessTokenVO resAccessTokenVO = JSON.parseObject(resp,ResAccessTokenVO.class);
        new ModelAndView("redirect:"+projectUrlConfig.getSell()
                .concat("/seller/login?openId=").concat(resAccessTokenVO.getOpenid()));
    }
}
