package com.miner.sell.controller;

import com.miner.sell.dto.OrderDTO;
import com.miner.sell.enums.ResultEnum;
import com.miner.sell.exception.SellException;
import com.miner.sell.serivce.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.HashMap;
import java.util.Map;

/**
 * 卖家端
 * @author 洪峰
 * @create 2018-01-23 19:49
 **/
@Controller
@RequestMapping("/sell/order")
@Slf4j
public class SellOrderController {

    @Autowired
    OrderServiceImpl orderService;

    /**
     * 获取所有订单
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ModelAndView getList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                @RequestParam(value = "size",defaultValue = "10") Integer size){
        PageRequest pageRequest = new PageRequest(page-1,size);
        Page<OrderDTO> orderDTOPage = orderService.getOrderList(pageRequest);
        Map<String,Object> map = new HashMap<>();
        map.put("orderDTOPage",orderDTOPage);
        map.put("currPage",page);
        map.put("size",size);
        return new ModelAndView("sellorder/list",map);
    }

    /**
     * 卖家端取消订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam(value = "orderId") String orderId,Map<String,String> map){
        try {
            OrderDTO orderDTO = orderService.getOrder(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("【卖家端取消订单】取消订单异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","list/");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url","list/");
        return new ModelAndView("common/success",map);

    }

    /**
     * 查询订单详情
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam(value = "orderId")String orderId,Map<String,Object> map){
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.getOrder(orderId);
        }catch (SellException e){
            log.error("【卖家端查询订单详情】查询订单异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","list/");
            return new ModelAndView("common/error",map);
        }
        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail",map);
    }

    /**
     * 卖家端完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam(value = "orderId")String orderId,Map<String,Object> map){
        try{
            OrderDTO orderDTO = orderService.getOrder(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            log.error("【卖家端完结订单】完结订单异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","list/");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url","list/");
        return new ModelAndView("common/success",map);
    }
}
