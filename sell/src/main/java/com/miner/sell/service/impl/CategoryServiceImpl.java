package com.miner.sell.service.impl;

import com.miner.sell.dataobject.ProductCategory;
import com.miner.sell.repository.ProductCategoryRepository;
import com.miner.sell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 洪峰
 * @create 2018-01-18 21:59
 **/
@Service
@CacheConfig(cacheNames = "product")
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    ProductCategoryRepository repository;

    /**
     * Cacheable将该方法返回的内容缓存值redis中
     * @param id
     * @return
     */
    @Override
//    @Cacheable(cacheNames = "product",key = "123")
    @CachePut(key = "123")
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


    /**
     * CachePut更新key为123的数据
     * CacheEvict删除key为123的数据
     * @param productCategory
     * @return
     */
    @Override
//    @CachePut(cacheNames = "product",key = "123")
//    @CacheEvict(cacheNames = "product",key = "123")
    @CachePut(key = "123")
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
