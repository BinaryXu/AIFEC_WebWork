package com.miner.sell.dataobject;

import com.miner.sell.enums.OrderStatusEnum;
import com.miner.sell.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 订单表
 * @author 洪峰
 * @create 2018-01-20 12:46
 **/
@Entity
@Data
public class OrderMaster {

    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态 初始值0-未支付*/
    private Integer payStatus = PayStatusEnum.FAIL.getCode();

    private Date createTime;

    private Date updateTime;
}
