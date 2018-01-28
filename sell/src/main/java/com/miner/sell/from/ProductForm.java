package com.miner.sell.from;

import com.miner.sell.enums.ProductStatusEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 洪峰
 * @create 2018-01-28 18:22
 **/
@Data
public class ProductForm {

    private String productId;

    /** 商品名称 */
    @NotEmpty(message = "商品名称必填")
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
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /** 对应类目 */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}
