package com.qwb.takeout.controller;

import com.qwb.takeout.constans.CookieConstans;
import com.qwb.takeout.constans.RedisConstans;
import com.qwb.takeout.enumCode.ExceptionEnum;
import com.qwb.takeout.model.entity.SellerInfo;
import com.qwb.takeout.service.SellerInfoService;
import com.qwb.takeout.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *商家用户controller
 *登陆、登出
 * @author SpringR
 */
@Controller
@RequestMapping("/back/user")
public class SellerUserController {

    @Autowired
    private SellerInfoService sellerInfoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     *登陆操作
     *
     * @author SpringR
     * @param   openid
     * @return
     */
    @GetMapping("/login")
    public ModelAndView sellerLogin(@RequestParam String openid,
                                    HttpServletResponse response,
                                    Map<String,Object> map){
        //1.openid与数据库做匹配
        SellerInfo sellerInfo = sellerInfoService.findOneByOpenid(openid);
        if(sellerInfo == null){
            map.put("msg", ExceptionEnum.SELLER_UNAUTHORIZED.getMsg());
            map.put("url","/");
            ModelAndView modelAndView = new ModelAndView("/common/error");
            return modelAndView.addAllObjects(map);
        }
        //2.设置token到redis
        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(String.format(RedisConstans.TOKEN_PREFIX,uuid),openid,RedisConstans.TOKEN_EXPIRE, TimeUnit.SECONDS);
        //3.设置token到cookie
        CookieUtil.setCookie(CookieConstans.TOKEN,uuid,CookieConstans.EXPIRE,CookieConstans.PATH,response);
        return new ModelAndView("redirect:"+"http://localhost:8088/sell/back/product/list.htm");
    }
}
