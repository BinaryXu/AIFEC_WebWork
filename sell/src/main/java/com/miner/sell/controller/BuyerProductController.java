package com.miner.sell.controller;

import com.miner.sell.VO.ProductInfoVO;
import com.miner.sell.VO.ProductVO;
import com.miner.sell.VO.ResultVO;
import com.miner.sell.dataobject.ProductCategory;
import com.miner.sell.dataobject.ProductInfo;
import com.miner.sell.service.CategoryService;
import com.miner.sell.service.ProductInfoService;
import com.miner.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 洪峰
 * @create 2018-01-19 22:37
 **/
@RestController
@RequestMapping(value = "buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product",key = "#sellerId", condition = "#sellerId.length()>3",unless = "#result.getCode() != 0")
    public ResultVO<Map<String,String>> getProdutList(@RequestParam("sellerId") String sellerId){

        //1.获取在售商品列表
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        //2.获取类目列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.组装响应报文
        List<ProductVO> productVOList = new ArrayList<ProductVO>();
        for(ProductCategory productCategory : productCategoryList){
            List<ProductInfoVO> productInfoVOList = new ArrayList<ProductInfoVO>();
            for(ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
}
