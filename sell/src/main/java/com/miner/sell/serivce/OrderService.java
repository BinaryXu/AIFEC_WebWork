package com.miner.sell.serivce;

import com.miner.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author hongf
 * @create 2018-01-20 14:48
 * @desc 订单的逻辑处理
 **/

public interface OrderService {

    /** 创建订单 */
    OrderDTO create(OrderDTO orderDTO);

    /**查询单个订单 */
    OrderDTO getOrder(String OrderId);

    /**查询订单列表 */
    Page<OrderDTO> getOrderList(String buyerOpenId,Pageable pageable);

    /**取消订单 */
    OrderDTO cancel(OrderDTO orderDTO);

    /**完结订单 */
    OrderDTO finish(OrderDTO orderDTO);

    /**支付订单 */
    OrderDTO payId(OrderDTO orderDTO);

    /** 卖家端使用 */
    Page<OrderDTO> getOrderList(Pageable pageable);

}
