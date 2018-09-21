package com.qwb.takeout.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qwb.takeout.model.entity.ProductCategory;
import com.qwb.takeout.model.entity.ProductInfo;
import com.qwb.takeout.service.ProductCategoryService;
import com.qwb.takeout.service.ProductInfoService;
import com.qwb.takeout.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 *商家后台管理---商品
 *
 * @author SpringR
 * @param
 * @return
 */
@Controller
@RequestMapping("/back/product")
public class SellProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     *商品列表
     *
     * @author SpringR
     * @param
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView productList(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "5") Integer pageSize){
        Page<ProductInfo> page = PageHelper.startPage(pageNum,pageSize);
        List<ProductInfo> productInfoList = productInfoService.findAllProduct();
        String productInfoListJson = null;
        productInfoListJson = JsonUtil.objectToJson(productInfoList);
        //stringRedisTemplate.opsForValue().set("productList",productInfoListJson,6000,TimeUnit.SECONDS);
        ModelAndView mv = new ModelAndView("/product/list");
        mv.addObject("productInfoPage",page.toPageInfo());
        return mv;
    }

    /**
     *跳转更新商品页面
     *
     * @author SpringR
     * @param
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public ModelAndView updateProduct(@RequestParam Long productId){
        ProductInfo productInfo = productInfoService.findOneById(productId);
        ModelAndView modelAndView = new ModelAndView("/product/index");
        List<ProductCategory> productCategoryList = productCategoryService.findAllCategory();
        modelAndView.addObject("productCategoryList",productCategoryList);
        modelAndView.addObject("productInfo",productInfo);
        return modelAndView;
    }

    /**
     *更新或者新添商品
     *
     * @author SpringR
     * @param
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveProduct(ProductInfo  productInfo){
        if(productInfo.getProductId() == null){
            try{
                productInfoService.saveProductInfo(productInfo);
            }catch (Exception e){
                ModelAndView modelAndView = new ModelAndView("/common/error");
                modelAndView.addObject("msg",e.getMessage());
                modelAndView.addObject("url","list");
                return modelAndView;
            }
        }else {
            try{
                productInfoService.updateProductInfo(productInfo);
            }catch (Exception e){
                ModelAndView modelAndView = new ModelAndView("/common/error");
                modelAndView.addObject("msg",e.getMessage());
                modelAndView.addObject("url","list");
                return modelAndView;
            }
        }
        ModelAndView modelAndView = new ModelAndView("/common/success");
        modelAndView.addObject("msg","更新成功");
        modelAndView.addObject("url","list");
        return modelAndView;
    }

    /**
     *跳转新添商品
     *
     * @author SpringR
     * @param
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public ModelAndView addProduct(){
        ModelAndView modelAndView = new ModelAndView("/product/index");
        List<ProductCategory> productCategoryList = productCategoryService.findAllCategory();
        modelAndView.addObject("productCategoryList",productCategoryList);
        return modelAndView;
    }

    @RequestMapping(value ="/off_sale", method = RequestMethod.GET)
    public ModelAndView offSaleProduct(@RequestParam String productId){
        try{
            productInfoService.changeProductStatus(1,Long.valueOf(productId));
        }catch (Exception e){
            ModelAndView modelAndView = new ModelAndView("/common/error");
            modelAndView.addObject("msg",e.getMessage());
            modelAndView.addObject("url","list");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("/common/success");
        modelAndView.addObject("msg","商品下架成功");
        modelAndView.addObject("url","list");
        return modelAndView;
    }

    @RequestMapping(value ="/on_sale", method = RequestMethod.GET)
    public ModelAndView onSaleProduct(@RequestParam String productId){
        try{
            productInfoService.changeProductStatus(0,Long.valueOf(productId));
        }catch (Exception e){
            ModelAndView modelAndView = new ModelAndView("/common/error");
            modelAndView.addObject("msg",e.getMessage());
            modelAndView.addObject("url","list");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("/common/success");
        modelAndView.addObject("msg","商品上架架成功");
        modelAndView.addObject("url","list");
        return modelAndView;
    }
}
