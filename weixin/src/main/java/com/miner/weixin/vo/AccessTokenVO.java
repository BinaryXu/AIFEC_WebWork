package com.miner.weixin.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author 洪峰
 * @create 2018-02-05 21:02
 **/
@Data
public class AccessTokenVO {

    @NotEmpty(message = "appid不能为空")
    private String appid;

    @NotEmpty(message = "secret不能为空")
    private String secret;

    @NotEmpty(message = "code不能为空")
    private String code;

    @NotEmpty(message = "grant_type不能为空")
    private String grant_type;

}
