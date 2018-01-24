package com.miner.sell.repository;

import com.miner.sell.dataobject.OrderMaster;
import org.hibernate.criterion.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    OrderMasterRepository repository;

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerAddress("三环新城");
        orderMaster.setBuyerAmount(new BigDecimal(12.5));
        orderMaster.setBuyerName("洪峰");
        orderMaster.setBuyerOpenid("110");
        orderMaster.setBuyerPhone("18511624762");
        orderMaster.setOrderId("111113");
        orderMaster.setUpdateTime(new Date());
        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderMaster> orderMasters = repository.findByBuyerOpenid("110",pageRequest);
        Assert.assertNotEquals(0,orderMasters.getTotalElements());
    }

    @Test
    public void findByBuyerOpenidAndOrderStatusNotIn() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderMaster> orderMasters = repository.findByBuyerOpenidAndOrderStatusNotIn("110",Arrays.asList(1),pageRequest);
        Assert.assertNotEquals(0,orderMasters.getTotalElements());
    }
}