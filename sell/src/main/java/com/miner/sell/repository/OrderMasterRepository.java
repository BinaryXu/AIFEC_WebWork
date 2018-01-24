package com.miner.sell.repository;

import com.miner.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author hongf
 * @create 2018-01-20 13:17
 * @desc 订单表操作
 **/

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    /**
     * 根据用户微信ID查询该用户所有订单(分页查询)
     * @param buyerOpenid
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

    /**
     * 查询未取消状态的订单
     * @param buyerOpenid
     * @param orderStatus
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenidAndOrderStatusNotIn(String buyerOpenid,List<Integer> orderStatus,Pageable pageable);
}
