package com.miner.weixin.controller;

import com.miner.weixin.dataobject.WeixinAppInfo;
import com.miner.weixin.enums.ResultEnum;
import com.miner.weixin.exception.WeixinException;
import com.miner.weixin.service.WeixinAppInfoService;
import com.miner.weixin.vo.AccessTokenVO;
import com.miner.weixin.vo.ResAccessTokenVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * accessToken操作
 * @author 洪峰
 * @create 2018-02-05 20:56
 **/
@RestController
@Slf4j
@RequestMapping("/sns/oauth2")
public class AccessTokenController {

    @Autowired
    WeixinAppInfoService appInfoService;

    @Autowired
    StringRedisTemplate redisTemplate;
    /**
     * 获取accessToken
     * @param accessTokenVO
     * @param bindingResult
     */
    @GetMapping("/access_token")
    public ResAccessTokenVO accessToken(@Valid AccessTokenVO accessTokenVO, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()) {
                log.error("【获取accessToken】必填项不能为空={}", bindingResult.getFieldError().getDefaultMessage());
                throw new WeixinException(ResultEnum.VALID_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
            }
            WeixinAppInfo weixinAppInfo = appInfoService.findByAppid(accessTokenVO.getAppid());
            if (weixinAppInfo == null) {
                log.error("【获取accessToken】APPID参数不正确={}", accessTokenVO.getAppid());
                throw new WeixinException(ResultEnum.APPID_ERROR);
            }
            if (!accessTokenVO.getSecret().equals(weixinAppInfo.getSecret())) {
                log.error("【获取accessToken】AppSecret参数不正确={}", accessTokenVO.getSecret());
                throw new WeixinException(ResultEnum.APPID_ERROR);
            }
            if (StringUtils.isEmpty(redisTemplate.opsForValue().get(accessTokenVO.getCode()))) {
                log.error("【获取accessToken】code不正确={}", accessTokenVO.getSecret());
                throw new WeixinException(ResultEnum.CODE_OVER);
            }
            String token = "123456";
            ResAccessTokenVO resAccessTokenVO = new ResAccessTokenVO();

            resAccessTokenVO.setAccess_token(token);
            resAccessTokenVO.setExpires_in(7200);
            resAccessTokenVO.setOpenid("TXTX456");
            resAccessTokenVO.setRefresh_token(token);
            resAccessTokenVO.setScope("scope");
            return resAccessTokenVO;
        }catch (Exception e){
            ResAccessTokenVO resAccessTokenVO = new ResAccessTokenVO();
            resAccessTokenVO.setErrcode(40029);
            resAccessTokenVO.setErrmsg("invalid code");
            return resAccessTokenVO;
        }
    }
}
