package com.miner.sell.service.impl;

import com.miner.sell.dataobject.OrderDetail;
import com.miner.sell.dataobject.OrderMaster;
import com.miner.sell.dataobject.ProductInfo;
import com.miner.sell.dto.CartDTO;
import com.miner.sell.dto.OrderDTO;
import com.miner.sell.enums.OrderStatusEnum;
import com.miner.sell.enums.PayStatusEnum;
import com.miner.sell.enums.ResultEnum;
import com.miner.sell.exception.SellException;
import com.miner.sell.repository.OrderDetailRepository;
import com.miner.sell.repository.OrderMasterRepository;
import com.miner.sell.service.OrderService;
import com.miner.sell.service.ProductInfoService;
import com.miner.sell.utils.KeyUtil;
import com.miner.sell.utils.OrderMasterToOrderDTOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 洪峰
 * @create 2018-01-20 15:03
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        //1.查询商品数量单价
        String orderId = KeyUtil.getUniqueKey();
        BigDecimal sumPrice = new BigDecimal(0.00);
        for(OrderDetail orderDetail: orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            //2.计算总价
            BigDecimal price = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()));
            sumPrice = price.add(sumPrice);

            //3.1存储订单详情表
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailRepository.save(orderDetail);
        }
        //3.2存储订单表
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setBuyerAmount(sumPrice);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.FAIL.getCode());
        orderMaster.setCreateTime(new Date());
        orderMasterRepository.save(orderMaster);
        //4.扣除库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.deductStork(cartDTOList);

        return orderDTO;
    }

    /**
     * 获取单个订单信息
     * @param OrderId
     * @return
     */
    @Override
    public OrderDTO getOrder(String OrderId) {
        //根据订单号查询订单表
        OrderMaster orderMaster = orderMasterRepository.findOne(OrderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        //根据订单号查询订单详情
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(OrderId);
        if (orderDetailList.size()<=0){
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    /**
     * 查询用户所有的订单信息
     * @param buyerOpenId
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderDTO> getOrderList(String buyerOpenId, Pageable pageable) {
        //查询该微信ID下的所有订单（分页）
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenId,pageable);
        List<OrderDTO> orderDTOList = OrderMasterToOrderDTOUtil.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    /**
     * 取消订单
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        //判斷訂單状态是否符合可退状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】订单状态不正确 订单号={}",orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单表的状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if(result == null){
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        //增加库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】订单中无产品详情，orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_NULL);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.insertStork(cartDTOList);

        //如果已支付，进行退款
        if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS)){
            //TODO
        }

        return orderDTO;
    }

    /**
     * 完結訂單
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if(!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())){
            log.error("【完结订单】订单状态不正确 订单号={},订单状态={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if(result == null){
            log.error("【完结订单】订单更新失败 订单号={},订单状态={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }

        return orderDTO;
    }

    /**
     * 支付订单
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO payId(OrderDTO orderDTO) {
        //判断订单状态
        if(!OrderStatusEnum.NEW.getCode().equals(orderDTO.getOrderStatus())){
            log.error("【支付订单】订单状态不正确 订单号={},订单状态={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断你支付状态
        if(!PayStatusEnum.FAIL.getCode().equals(orderDTO.getPayStatus())){
            log.error("【支付订单】支付状态不正确 订单号={},支付状态={}",orderDTO.getOrderId(),orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        if(result == null){
            log.error("【支付订单】支付更新失败 订单号={},支付状态={}",orderDTO.getOrderId(),orderDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        return orderDTO;
    }

    /**
     * 查询所有的订单信息
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderDTO> getOrderList(Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMasterToOrderDTOUtil.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList,pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }
}
