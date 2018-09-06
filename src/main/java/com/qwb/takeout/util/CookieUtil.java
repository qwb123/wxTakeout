package com.qwb.takeout.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    /**
     * 设置cookie
     * @param key
     * @param value
     * @param expire 过期时间
     * @param path   应用路径
     * @return
     */
    public static void setCookie(String key, String value, Integer expire, String path, HttpServletResponse response){
        Cookie cookie = new Cookie(key,value);
        cookie.setMaxAge(expire);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

}
