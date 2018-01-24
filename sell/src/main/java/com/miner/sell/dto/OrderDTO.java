package com.miner.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miner.sell.dataobject.OrderDetail;
import com.miner.sell.enums.OrderStatusEnum;
import com.miner.sell.enums.PayStatusEnum;
import com.miner.sell.utils.EnumUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 洪峰
 * @create 2018-01-20 14:56
 **/
@Data
public class OrderDTO {

    private String orderId;

    /** 买家名称 */
    private String buyerName;

    /** 买家电话 */
    private String buyerPhone;

    /** 买家地址 */
    private String buyerAddress;

    /** 买家微信ID */
    private String buyerOpenid;

    /** 订单总额 */
    private BigDecimal buyerAmount;

    /** 订单状态 初始值0-已下单*/
    private Integer orderStatus;

    /** 支付状态 初始值0-未支付*/
    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
