package com.miner.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 洪峰
 * @create 2018-01-19 22:44
 **/
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 7824754419764787060L;
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("Type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
