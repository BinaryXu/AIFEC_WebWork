package com.miner.weixin.vo;

import lombok.Data;

/**
 * @author 洪峰
 * @create 2018-02-08 20:43
 **/
@Data
public class ResAccessTokenVO {

    private String access_token;

    private Integer expires_in;

    private String refresh_token;

    private String openid;

    private String scope;

    private String unionid;

    private Integer errcode;

    private String errmsg;
}
