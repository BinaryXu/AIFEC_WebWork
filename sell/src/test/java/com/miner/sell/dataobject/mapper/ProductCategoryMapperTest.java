package com.miner.sell.dataobject.mapper;

import com.miner.sell.dataobject.ProductCategory;
import com.sun.glass.ui.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import javax.websocket.server.ServerContainer;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper categoryMapper;

    @Test
    public void insertByMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("categoryName","老板力荐");
        map.put("categoryType",200);
        map.put("categoryId",334);
        int result = categoryMapper.insertByMap(map);
        Assert.assertEquals(1,result);

    }

    @Test
    public void insertByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(224);
        productCategory.setCategoryName("我的最爱");
        productCategory.setCategoryType(106);
        int result = categoryMapper.insertByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findByCategoryType() {
        ProductCategory productCategory = categoryMapper.findByCategoryType(334);
    }
}