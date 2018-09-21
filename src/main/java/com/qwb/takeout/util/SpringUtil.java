package com.qwb.takeout.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring 工具类
 * 通过实现ApplicationContextAware接口  来获取ApplicationContext对象
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(applicationContext == null){
            SpringUtil.applicationContext = applicationContext;
        }
    }

    /**
     *通过bean name获取bean对象
     *
     * @author SpringR
     * @param
     * @return
     */
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    /**
     *通过class name 获取bean对象
     *
     * @author SpringR
     * @param
     * @return
     */
    public static <T> T getBean(Class<T> classType){
        return applicationContext.getBean(classType);
    }

    /**
     *通过name 已经class 指定获取bean
     *
     * @author SpringR
     * @param
     * @return
     */
    public static <T> T getBean(String name,Class<T> classType){
        return  applicationContext.getBean(name, classType);
    }
}
