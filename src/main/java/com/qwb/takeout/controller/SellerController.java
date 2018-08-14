package com.qwb.takeout.controller;


import com.qwb.takeout.serviceImpl.SellerInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.qwb.takeout.model.entity.SellerInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qwb.takeout.annotation.JsonFilter;
import com.github.pagehelper.*;

@RequestMapping("/seller")
@Controller("SellerController")
public class SellerController {

    @Autowired
    private SellerInfoServiceImpl sellerInfoService;

    @JsonFilter(type = PageInfo.class, include = {"pageNum", "pageSize", "pages", "total", "list"})
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public
    @ResponseBody
     PageInfo<SellerInfo> getAllList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "20") Integer pageSize) {
        Page<SellerInfo> page = PageHelper.startPage(pageNum, pageSize);
        sellerInfoService.findAllList();
        return page.toPageInfo();
    }



}
