package com.miner.sell.serivce.impl;

import com.miner.sell.dto.OrderDTO;
import com.miner.sell.enums.ResultEnum;
import com.miner.sell.exception.SellException;
import com.miner.sell.serivce.BuyerService;
import com.miner.sell.serivce.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 洪峰
 * @create 2018-01-21 14:32
 **/
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    OrderServiceImpl orderService;

    /**
     * 根据订单号查询订单详情
     * @param openId
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO findOrderOne(String openId, String orderId) {
        OrderDTO orderDTO =  checkOrderOwner(openId,orderId);
        return orderDTO;
    }

    /**
     * 根据订单号取消订单
     * @param openId
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO cancelOrder(String openId, String orderId) {
        OrderDTO orderDTO =  checkOrderOwner(openId,orderId);
        if(orderDTO == null){
            log.error("【取消订单】订单不存在={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        return orderService.cancel(orderDTO);
    }

    public OrderDTO checkOrderOwner(String openId, String orderId){
        OrderDTO orderDTO = orderService.getOrder(orderId);
        if(orderDTO == null){
            return null;
        }
        if(!orderDTO.getBuyerOpenid().equals(openId)){
            log.error("【订单查询】查不到该订单号={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_USER);
        }
        return orderDTO;
    }
}
