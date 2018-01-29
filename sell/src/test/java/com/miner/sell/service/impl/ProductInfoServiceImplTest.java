package com.miner.sell.service.impl;

import com.miner.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    ProductInfoServiceImpl productInfoService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productInfoService.findOne("6666");
        Assert.assertEquals("6666",productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setCategoryType(2);
        productInfo.setProductDescription("今日爆款");
        productInfo.setProductIcon("http://123.jpg");
        productInfo.setProductId("8888");
        productInfo.setProductName("西红柿鸡蛋");
        productInfo.setProductPrice(new BigDecimal("14.6"));
        productInfo.setProductStatus(1);
        productInfo.setProductStock(100);
        ProductInfo result = productInfoService.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<ProductInfo> productInfos =  productInfoService.findAll(pageRequest);
        Assert.assertNotEquals(0,productInfos.getTotalElements());
    }
}