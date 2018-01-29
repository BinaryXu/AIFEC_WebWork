package com.miner.sell.service.impl;

import com.miner.sell.dataobject.ProductCategory;
import com.miner.sell.repository.ProductCategoryRepository;
import com.miner.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 洪峰
 * @create 2018-01-18 21:59
 **/
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }


    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
