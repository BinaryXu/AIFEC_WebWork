package com.miner.sell.repository;

import com.miner.sell.dataobject.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    SellerInfoRepository repository;

    @Test
    public void saveTest(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId("6666");
        sellerInfo.setCreateTime(new Date());
        sellerInfo.setOpenId("weixin123");
        sellerInfo.setPassWord("123456");
        sellerInfo.setUserName("xhf");
        SellerInfo result = repository.save(sellerInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOpenId() {
       SellerInfo sellerInfo = repository.findByOpenId("weixin123");
       Assert.assertEquals("6666",sellerInfo.getSellerId());
    }
}