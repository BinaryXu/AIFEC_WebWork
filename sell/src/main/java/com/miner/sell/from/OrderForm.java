package com.miner.sell.from;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author 洪峰
 * @create 2018-01-21 13:08
 **/

@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "电话必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "微信ID必填")
    private String openid;

    @NotEmpty(message = "购物车列表不能为空")
    private String items;
}
