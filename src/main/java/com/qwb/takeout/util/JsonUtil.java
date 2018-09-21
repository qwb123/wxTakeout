package com.qwb.takeout.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     *不可以实例化
     *避免实例化多个对象浪费资源
     * @author SpringR
     * @param
     * @return
     */
    private JsonUtil(){}

    /**
     *将对象转化为字符串并放回该字符串
     *
     * @author SpringR
     * @param
     * @return
     */
    public static String objectToJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     *将json转化为字符串
     *
     * @author SpringR
     * @param
     * @return
     */
    public static <T> T jsonToObject(String json,Class<T> valueType){

        try{
            objectMapper.readValue(json,valueType);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }
}
