package com.miner.sell.repository;

import com.miner.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hongf
 * @create 2018-01-19 20:14
 * @desc
 **/

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>{

    List<ProductInfo> findByProductStatus(Integer status);
}
