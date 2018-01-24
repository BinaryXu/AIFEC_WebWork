package com.miner.sell.serivce;

import com.miner.sell.dataobject.ProductInfo;
import com.miner.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author hongf
 * @create 2018-01-19 20:52
 * @desc 商品逻辑实现类
 **/

public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /**
     * 查询在售的商品
     * @return
     */
    List<ProductInfo> findUpAll();

    ProductInfo save(ProductInfo productInfo);

    //分页查询
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 扣除库存
     * @param cartDTOList
     */
    void deductStork(List<CartDTO> cartDTOList);

    /**
     * 增加库存
     * @param cartDTOList
     */
    void insertStork(List<CartDTO> cartDTOList);
}
