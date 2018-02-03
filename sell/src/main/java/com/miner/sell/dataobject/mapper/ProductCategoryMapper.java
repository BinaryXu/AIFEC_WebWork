package com.miner.sell.dataobject.mapper;

import com.miner.sell.dataobject.ProductCategory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author 洪峰
 * @create 2018-01-31 20:24
 **/

public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name,category_type,category_id) values (#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=DOUBLE},#{categoryId,jdbcType=DOUBLE})")
    int insertByMap(Map<String,Object> map);

    @Insert("insert into product_category(category_name,category_type,category_id) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=DOUBLE},#{categoryId,jdbcType=DOUBLE})")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type=#{categoryType}")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType"),
    })
    ProductCategory findByCategoryType(Integer categoryType);


}
