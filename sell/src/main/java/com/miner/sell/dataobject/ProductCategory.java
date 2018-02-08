package com.miner.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * 类目表
 * @author 洪峰
 * @create 2018-01-18 20:17
 **/
@Entity
@Data
public class ProductCategory implements Serializable{

    private static final long serialVersionUID = -4998554180829318126L;
    /** 类目ID */
    @Id
    @GeneratedValue
    private Integer categoryId;

    /** 类目名称 */
    private String categoryName;

    /** 类目编号 */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;


}
