package com.qwb.takeout.controller;

import com.qwb.takeout.annotation.JsonFilter;
import com.qwb.takeout.model.BaseResponse;
import com.qwb.takeout.model.entity.ProductCategory;
import com.qwb.takeout.model.entity.ProductInfo;
import com.qwb.takeout.model.vo.BuyerProductListVo;
import com.qwb.takeout.service.ProductCategoryService;
import com.qwb.takeout.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取商品
 */

@RequestMapping("/sell")
@Controller("ProductController")
public class ProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 获取商品列表
     * @return
     */
    @GetMapping("/buyer/product/list")
    @JsonFilter(type = ProductInfo.class,include = {"id","name","price","description","inco"})
    public
    @ResponseBody
    BaseResponse<List<BuyerProductListVo>> buyerProductList(){
        List<ProductInfo> productInfos = productInfoService.findAllProduct();
        List<ProductCategory> productCategories = productCategoryService.findAllCategory();
        List<BuyerProductListVo> listVos = new ArrayList();
        for (ProductCategory productCategory : productCategories){
            BuyerProductListVo buyerProductListVo = new BuyerProductListVo();
            buyerProductListVo.setType(productCategory.getCategoryType());
            buyerProductListVo.setName(productCategory.getCategoryName());
            listVos.add(buyerProductListVo);
        }
        for(ProductInfo productInfo :productInfos){
            for(BuyerProductListVo buyerProductListVo: listVos){
                if(buyerProductListVo.getType() == productInfo.getCategoryType()){
                    buyerProductListVo.getFoods().add(productInfo);
                    break;
                }
            }
        }
        return BaseResponse.success(listVos);
    }
}
