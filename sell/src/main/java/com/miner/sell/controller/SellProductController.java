package com.miner.sell.controller;

import com.miner.sell.dataobject.ProductCategory;
import com.miner.sell.dataobject.ProductInfo;
import com.miner.sell.enums.ResultEnum;
import com.miner.sell.exception.SellException;
import com.miner.sell.from.ProductForm;
import com.miner.sell.service.CategoryService;
import com.miner.sell.service.ProductInfoService;
import com.miner.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 洪峰
 * @create 2018-01-26 20:16
 **/
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellProductController {

    @Autowired
    ProductInfoService productInfoService;

    @Autowired
    CategoryService categoryService;

    /**
     * 获取商品列表
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ModelAndView getProductList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                       @RequestParam(value = "size",defaultValue = "10") Integer size){
        PageRequest pageRequest = new PageRequest(page-1,size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);
        Map<String,Object> map = new HashMap<>();
        map.put("currPage",page);
        map.put("size",size);
        map.put("productInfoPage",productInfoPage);
        return new ModelAndView("/product/list",map);
    }

    /**
     * 更新商品状态
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/off")
    public ModelAndView off(@RequestParam("productId") String productId,Map<String,Object> map){
       try {
           productInfoService.offSet(productId);
       }catch (SellException e){
           log.error("【卖家端上架】商品状态更新异常{}",e);
           map.put("msg",e.getMessage());
           map.put("url","list/");
           return new ModelAndView("common/error",map);
       }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url","list/");
        return  new ModelAndView("common/success",map);
    }

    /**
     * 获取需要修改的产品
     * @param productId
     * @param map
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,
                      Map<String,Object> map){

        if(!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productInfoService.findOne(productId);
            if (productInfo == null){
                log.error("【卖家端修改商品】修改的商品不存在");
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            map.put("productInfo",productInfo);
        }
        List<ProductCategory> productCategories = categoryService.findAll();
        map.put("productCategories",productCategories);

        return new ModelAndView("product/index",map);

    }

    /**
     * 修改或新增产品
     * @param productForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if(bindingResult.hasErrors()){
            log.error("【修改商品】={}",bindingResult.getFieldError().getDefaultMessage());
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","list/");
            return new ModelAndView("common/error",map);
        }
        try{
            ProductInfo productInfo = new ProductInfo();
            if(StringUtils.isEmpty(productForm.getProductId())){
                productForm.setProductId(KeyUtil.getUniqueKey());
                productForm.setCreateTime(new Date());
            }else {
                productInfo = productInfoService.findOne(productForm.getProductId());
                productForm.setUpdateTime(new Date());
                productForm.setCreateTime(productInfo.getCreateTime());
            }
            BeanUtils.copyProperties(productForm,productInfo);
            productInfoService.save(productInfo);
            map.put("msg", ResultEnum.SUCCESS.getMessage());
            map.put("url","list/");
            return  new ModelAndView("common/success",map);
        }catch (SellException e){
            log.error("【修改商品】={}",e);
            map.put("msg",e.getMessage());
            map.put("url","list/");
            return new ModelAndView("common/error",map);
        }

    }
}
