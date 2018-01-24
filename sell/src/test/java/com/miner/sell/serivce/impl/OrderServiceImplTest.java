package com.miner.sell.serivce.impl;

import com.miner.sell.dataobject.OrderDetail;
import com.miner.sell.dataobject.OrderMaster;
import com.miner.sell.dto.OrderDTO;
import com.miner.sell.enums.OrderStatusEnum;
import com.miner.sell.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    private static final String OpenId = "110";
    @Autowired
    OrderServiceImpl orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("三环新城");
        orderDTO.setBuyerName("洪峰");
        orderDTO.setBuyerOpenid(OpenId);
        orderDTO.setBuyerPhone("18511624762");
        orderDTO.setCreateTime(new Date());

        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("6666");
        orderDetail.setProductQuantity(5);
        orderDetailList.add(orderDetail);
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("创建订单{}",result);
    }

    @Test
    public void getOrder() {
        OrderDTO orderDTO = orderService.getOrder("1516440607909231558");
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void getOrderList() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage = orderService.getOrderList("110",pageRequest);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.getOrder("1516440607909231558");
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.getOrder("1516440607909231558");
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void payId() {
        OrderDTO orderDTO = orderService.getOrder("1516440607909231558");
        OrderDTO result = orderService.payId(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }
}