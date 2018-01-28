package com.miner.sell.controller;

import com.miner.sell.VO.ResultVO;
import com.miner.sell.dataobject.OrderMaster;
import com.miner.sell.dto.OrderDTO;
import com.miner.sell.enums.ResultEnum;
import com.miner.sell.exception.SellException;
import com.miner.sell.from.OrderForm;
import com.miner.sell.repository.OrderMasterRepository;
import com.miner.sell.serivce.BuyerService;
import com.miner.sell.serivce.OrderService;
import com.miner.sell.serivce.impl.BuyerServiceImpl;
import com.miner.sell.serivce.impl.OrderServiceImpl;
import com.miner.sell.utils.OrderFormToOrderDTOUtil;
import com.miner.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 洪峰
 * @create 2018-01-21 13:01
 **/

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    BuyerService buyerService;


    //创建订单
    @PostMapping(value = "/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】请求参数不正确={}",orderForm);
            throw new SellException(ResultEnum.REQUEST_PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderFormToOrderDTOUtil.convert(orderForm);

        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】订单中购物车不能为空={}",orderDTO.getOrderDetailList());
            throw new SellException(ResultEnum.REQUEST_CART_ERROR);
        }
        OrderDTO result = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderid",result.getOrderId());
        return ResultVOUtil.success(map);
    }
    //订单列表
    @GetMapping(value = "/list")
    public ResultVO getList(@RequestParam(value = "openid") String openid,
                            @RequestParam(value = "page",defaultValue = "0") Integer page,
                            @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("微信ID不能为空");
            throw new SellException(ResultEnum.REQUEST_WEIID_ERROR);
        }
        PageRequest pageRequest = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage =  orderService.getOrderList(openid,pageRequest);
        return ResultVOUtil.success(orderDTOPage.getContent());
    }
    //订单详情
    @GetMapping(value = "/detail")
    public ResultVO getDetailList(@RequestParam(value = "openId") String openId,
                                  @RequestParam(value = "orderId") String orderId){

        OrderDTO orderDTO = buyerService.findOrderOne(openId,orderId);

        return ResultVOUtil.success(orderDTO);
    }

    //取消订单
    @GetMapping("/cancel")
    public ResultVO cancel(@RequestParam(value = "openId") String openId,
                           @RequestParam(value = "orderId") String orderId){
        buyerService.cancelOrder(openId,orderId);
        return ResultVOUtil.success();
    }
}
