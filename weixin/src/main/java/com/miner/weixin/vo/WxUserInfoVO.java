package com.miner.weixin.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author 洪峰
 * @create 2018-02-04 14:50
 **/
@Data
public class WxUserInfoVO {

    /**
     * 应用唯一标识
     */
    @NotEmpty(message = "应用唯一标识不能为空")
    private String appid;

    /**
     * 请使用urlEncode对链接进行处理(重定向地址)
     */
    @NotEmpty(message = "redirect_uri不能为空")
    private String redirect_uri;

    /**
     * 默认填code
     */
    @NotEmpty(message = "response_type不能为空")
    private String response_type;

    /**
     * 应用授权作用域，拥有多个作用域用逗号（,）分隔
     * 网页应用目前仅填写snsapi_login即
     */
    @NotEmpty(message = "scope不能为空")
    private String scope;

    /**
     * 用于保持请求和回调的状态，授权请求后原样带回给第三方。
     * 该参数可用于防止csrf攻击（跨站请求伪造攻击），
     * 建议第三方带上该参数，可设置为简单的随机数加session进行校验
     */
    @NotEmpty(message = "state不能为空")
    private String state;
}
