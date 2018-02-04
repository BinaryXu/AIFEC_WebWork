package com.miner.weixin.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 洪峰
 * @create 2018-02-04 15:47
 **/
@Table(name = "a_weixin_app_info")
@Entity
@Data
public class WeixinAppInfo {

    @Id
    private String appId;

    private String orderId;

    private String secret;

    private Integer status;

    private Date insertTime;

    private Date updateTime;
}
