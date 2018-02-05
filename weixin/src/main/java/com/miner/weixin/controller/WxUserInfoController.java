package com.miner.weixin.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.miner.weixin.config.ProjectUrlConfig;
import com.miner.weixin.constant.RedisConstant;
import com.miner.weixin.dataobject.WeixinAppInfo;
import com.miner.weixin.enums.ResultEnum;
import com.miner.weixin.exception.WeixinException;
import com.miner.weixin.service.WeixinAppInfoService;
import com.miner.weixin.utils.BaseUtil;
import com.miner.weixin.utils.QRCodeUtil;
import com.miner.weixin.vo.WxUserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 洪峰
 * @create 2018-02-04 14:33
 **/
@Controller
@RequestMapping("/connect")
@Slf4j
public class WxUserInfoController {

    @Autowired
    WeixinAppInfoService appInfoService;

    @Autowired
    ProjectUrlConfig projectUrlConfig;

    @Autowired
    StringRedisTemplate redisTemplate;


    @GetMapping(value = "/qrconnect")
    public ModelAndView getQrconnect(@Valid WxUserInfoVO wxUserInfoVO,
                                     BindingResult bindingResult,
                                     Map<String,Object> map){
        try {
            if (bindingResult.hasErrors()){
                log.error("【获取二维码】强制性校验={}",bindingResult.getFieldError().getDefaultMessage());
                throw new WeixinException(ResultEnum.VALID_ERROR.getCode(),
                        bindingResult.getFieldError().getDefaultMessage());
            }
            WeixinAppInfo weixinAppInfo = appInfoService.findByAppid(wxUserInfoVO.getAppid());
            if(weixinAppInfo == null){
                log.error("【获取二维码】APPID参数不正确={}",wxUserInfoVO.getAppid());
                throw new WeixinException(ResultEnum.APPID_ERROR);
            }
            String uuid = BaseUtil.getUUID();
            Integer expire = RedisConstant.EXPIRE;
            //生成二维码返回
            String url = projectUrlConfig.getRootUrl()
                    .concat("/connect/confirm?uuid=")
                    .concat(uuid);
            map.put("qrcode",QRCodeUtil.getQRCode(url));
            Map<String,String> redisMap = new HashMap<>();
            redisMap.put("redirectUrl",wxUserInfoVO.getRedirect_uri());
            redisMap.put("state",wxUserInfoVO.getState());
            //将uuid增加至缓存
            redisTemplate.opsForHash().putAll(uuid,redisMap);
            redisTemplate.expire(uuid,expire,TimeUnit.SECONDS);

            return new ModelAndView("qrcode/qrcode",map);
        }catch (Exception e){
            log.error("【获取二维码】生成二维码异常");
            throw new WeixinException(ResultEnum.QRCODE_ERROR);
        }
    }

    /**
     * 获取Code接口
     * @param uuid
     */
    @GetMapping("/confirm")
    public ModelAndView confirm(@RequestParam("uuid")String uuid){
        if(StringUtils.isEmpty(uuid)){
            log.error("【获取CODE】uuid不能为空！");
            throw new WeixinException(ResultEnum.UUID_NULL);
        }
        Map<Object, Object> redisMap = redisTemplate.opsForHash().entries(uuid);
        if(redisMap == null){
            log.error("【获取Code】二维码未过期或不存在！");
            throw new WeixinException(ResultEnum.QRCODE_OVER);
        }
        String code = BaseUtil.getUUID();
        String redirectUrl = (String)redisMap.get("redirectUrl");
        String state = (String)redisMap.get("state");

        redisTemplate.opsForValue().set(code,RedisConstant.CONTENT,RedisConstant.EXPIRE,TimeUnit.SECONDS);
        return new ModelAndView("redirect:"
                                .concat(redirectUrl)
                                .concat("?code=")
                                .concat(code)
                                .concat("&state=")
                                .concat(state));

    }

}
