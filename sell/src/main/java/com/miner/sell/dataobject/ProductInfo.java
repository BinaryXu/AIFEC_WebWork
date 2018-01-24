package com.miner.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 *
 * 商品信息表
 * @author 洪峰
 * @create 2018-01-19 20:08
 **/
@Entity
@Data
public class ProductInfo {

    /**  商品ID. */
    @Id
    private String productId;

    /** 商品名称 */
    private String productName;

    /** 商品小圖 */
    private String productIcon;

    /** 商品单价 */
    private BigDecimal productPrice;

    /** 商品库存 */
    private Integer productStock;

    /** 商品描述 */
    private String productDescription;

    /** 商品状态 */
    private Integer productStatus;

    /** 对应类目 */
    private Integer categoryType;
}
