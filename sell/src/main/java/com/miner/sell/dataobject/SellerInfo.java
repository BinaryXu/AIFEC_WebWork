package com.miner.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author 洪峰
 * @create 2018-01-29 19:36
 **/

@Entity
@Data
public class SellerInfo {

    @Id
    private String sellerId;

    /** 用户名 */
    private String userName;

    /** 密码 */
    private String passWord;

    /** 微信openID */
    private String openId;

    private Date createTime;

    private Date updateTime;
}
