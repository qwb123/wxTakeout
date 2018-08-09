package com.qwb.takeout.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
// 让方法支持多重注解
@Repeatable(JsonFilters.class)
public @interface JsonFilter {
    Class<?> type();

    String[] include() default {};

    String[] filter() default {};
}
