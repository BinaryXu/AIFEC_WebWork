package com.miner.sell.dto;

import lombok.Data;

/**
 * @author 洪峰
 * @create 2018-01-20 16:31
 **/
@Data
public class CartDTO {


    private String productId;

    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
