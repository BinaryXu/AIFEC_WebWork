package com.miner.sell.controller;

import com.miner.sell.dataobject.ProductCategory;
import com.miner.sell.enums.ResultEnum;
import com.miner.sell.exception.SellException;
import com.miner.sell.serivce.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 洪峰
 * @create 2018-01-28 19:33
 **/
@Controller
@RequestMapping(value = "/category")
@Slf4j
public class SellCategoryController {

    @Autowired
    CategoryService categoryService;

    //获取类目列表
    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("productCategoryList",productCategoryList);
        return new ModelAndView("category/list",map);
    }

    //展示类目
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                              Map<String,Object> map){
        if(categoryId != null){
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("productCategory",productCategory);
        }
        return new ModelAndView("category/index",map);
    }

    //保存类目
    @GetMapping("/save")
    public ModelAndView save(@RequestParam(value = "categoryName") String categoryName,
                             @RequestParam(value = "categoryType") Integer categoryType,
                             @RequestParam(value = "categoryId") Integer categoryId,
                             Map<String,Object> map){
        ProductCategory productCategory = new ProductCategory();
        if(categoryId != null){
            productCategory = categoryService.findOne(categoryId);
        }else{
            productCategory.setCreateTime(new Date());
        }
        productCategory.setCategoryType(categoryType);
        productCategory.setCategoryName(categoryName);
        productCategory.setUpdateTime(new Date());
        categoryService.save(productCategory);

        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url","list/");
        return  new ModelAndView("common/success",map);
    }

    //删除类目
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam(value = "categoryId") Integer categoryId,
                              Map<String,Object> map){
        if(categoryId == null){
            log.error("【删除类目】类目ID为空");
            map.put("msg", ResultEnum.CATEGORY_NOT_EXIT.getMessage());
            map.put("url","list/");
            return  new ModelAndView("common/error",map);
        }
        try{
            categoryService.delete(categoryId);
            map.put("msg", ResultEnum.SUCCESS.getMessage());
            map.put("url","list/");
            return new ModelAndView("common/success",map);
        }catch (SellException e){
            log.error("【删除类目】删除类目失败");
            map.put("msg", ResultEnum.CATEGORY_ERROR.getMessage());
            map.put("url","list/");
            return  new ModelAndView("common/error",map);
        }
    }

}
