package com.qwb.takeout.util;

import com.qwb.takeout.enumCode.EnumCommon;

public class EnumUtil {

    public static <T extends EnumCommon> T getEnum(Integer code, Class<T> enumClass){
        for(T each: enumClass.getEnumConstants()){
            if(each.getCode() == (int)code){
                return each;
            }
        }
        return null;
    }
}
