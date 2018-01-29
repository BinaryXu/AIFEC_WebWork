package com.miner.sell.service;

import com.miner.sell.dto.OrderDTO;

/**
 * @author hongf
 * @create 2018-01-21 14:30
 * @desc 买家查询详情取消订单
 **/

public interface BuyerService {

    /**
     * 根据订单号查询订单详情
     * @param openId
     * @param orderId
     * @return
     */
    OrderDTO findOrderOne(String openId,String orderId);

    /**
     * 根据订单号取消订单
     * @param openId
     * @param orderId
     * @return
     */
    OrderDTO cancelOrder(String openId,String orderId);
}
