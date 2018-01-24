package com.miner.sell.repository;

import com.miner.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hongf
 * @create 2018-01-20 13:22
 * @desc 订单详情操作
 **/

public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    /**
     * 根据订单ID查询订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}
