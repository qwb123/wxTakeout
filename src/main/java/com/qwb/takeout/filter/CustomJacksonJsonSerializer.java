package com.qwb.takeout.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qwb.takeout.annotation.JsonFilter;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * JsonFilter注解解析过滤器
 */
public class CustomJacksonJsonSerializer {
    private ObjectMapper mapper = new ObjectMapper();
    private JacksonJsonFilter jacksonFilter = new JacksonJsonFilter();

    {
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    }
    /**
     * @param clazz   target type
     * @param include include fields
     * @param filter  filter fields
     */
    public void filter(Class<?> clazz, String[] include, String[] filter) {
        if (clazz == null) {
            return;
        }
        if (include != null && include.length > 0) {
            jacksonFilter.include(clazz, include);
        }
        if (filter != null && filter.length > 0) {
            jacksonFilter.filter(clazz, filter);
        }
        mapper.addMixIn(clazz, jacksonFilter.getClass());
    }

    public String toJson(Object object) throws JsonProcessingException {
        mapper.setFilterProvider(jacksonFilter);
        return mapper.writeValueAsString(object);
    }

    public void filter(JsonFilter json) {
        this.filter(json.type(), json.include(), json.filter());
    }
}
