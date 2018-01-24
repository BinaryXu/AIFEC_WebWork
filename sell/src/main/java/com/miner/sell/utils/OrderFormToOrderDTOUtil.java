package com.miner.sell.utils;

import com.alibaba.fastjson.JSONArray;
import com.miner.sell.dataobject.OrderDetail;
import com.miner.sell.dataobject.OrderMaster;
import com.miner.sell.dto.OrderDTO;
import com.miner.sell.enums.ResultEnum;
import com.miner.sell.exception.SellException;
import com.miner.sell.from.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 *
 * @author 洪峰
 * @create 2018-01-21 13:19
 **/
@Slf4j
public class OrderFormToOrderDTOUtil {

    /**
     * 将前端传入的数据转换为后台处理数据
     * @param orderForm
     * @return
     */
    public static OrderDTO convert(OrderForm orderForm){
        try {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setBuyerName(orderForm.getName());
            orderDTO.setBuyerPhone(orderForm.getPhone());
            orderDTO.setBuyerAddress(orderForm.getAddress());
            orderDTO.setBuyerOpenid(orderForm.getOpenid());
            List<OrderDetail> orderDetailList = JSONArray.parseArray(orderForm.getItems(), OrderDetail.class);
            orderDTO.setOrderDetailList(orderDetailList);

            return orderDTO;
        }catch (Exception e){
            log.error("请求参数不正确={}",orderForm);
            throw new SellException(ResultEnum.REQUEST_PARAM_ERROR);
        }
    }
}
