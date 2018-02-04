package com.miner.weixin.dataobject;

import com.miner.weixin.enums.UserStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 微信用户表
 * @author 洪峰
 * @create 2018-02-04 14:02
 **/
@Table(name = "a_weixin_user_info")
@Entity
@Data
public class WeixinUserInfo {

    @Id
    private String userId;

    /**
     * 微信真实ID
     */
    private String orderId;

    /**
     * code参数
     */
    private String weixinCode;

    /**
     * token参数
     */
    private String weixinAccessToken;

    /**
     * access_token接口调用凭证超时时间
     */
    private Integer weixinExpiresIn;

    /**
     * 用户刷新access_token
     */
    private String weixinRefreshToken;

    /**
     * 微信orderID
     */
    private String weixinOrderId;

    /**
     * 用户状态0-正常  1-失效
     */
    private Integer status = UserStatusEnum.NORMAL.getCode();

    /**
     * 用户最后登陆时间
     */
    private Date lastTime;

    private Date insertTime;

    private Date updateTime;
}
