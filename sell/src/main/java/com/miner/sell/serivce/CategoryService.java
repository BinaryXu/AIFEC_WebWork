package com.miner.sell.serivce;

import com.miner.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * @author 洪峰
 * @create 2018-01-18 21:52
 **/

public interface CategoryService {

    ProductCategory findOne(Integer id);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

    void delete(Integer id);

}
