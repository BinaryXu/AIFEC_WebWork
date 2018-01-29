package com.miner.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 洪峰
 * @create 2018-01-29 21:42
 **/
@Component
@ConfigurationProperties(prefix = "projectUrl")
@Data
public class ProjectUrlConfig {

    /**
     * 点餐系统地址
     */
    public String sell;
}
