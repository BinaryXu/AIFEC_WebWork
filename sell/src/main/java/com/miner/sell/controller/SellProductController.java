package com.miner.sell.controller;

import com.miner.sell.dataobject.ProductInfo;
import com.miner.sell.enums.ResultEnum;
import com.miner.sell.exception.SellException;
import com.miner.sell.serivce.impl.ProductInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 洪峰
 * @create 2018-01-26 20:16
 **/
@Controller
@RequestMapping("/product")
@Slf4j
public class SellProductController {

    @Autowired
    ProductInfoServiceImpl productInfoService;

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
}
