package com.example.springbootdemo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    /**
     * 把对象转成json字符串
     * @param obj
     * @return
     */
    public static String convertJsonString(Object obj){
        ObjectMapper objectMapper = new ObjectMapper();
        String str = null;
        try{
            str = objectMapper.writeValueAsString(obj);
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 把json转成对象
     * @param json
     * @param objClass
     * @param <T>
     * @return
     */
    public static <T> T convertObj(String json, Class<T> objClass){
        ObjectMapper objectMapper = new ObjectMapper();
        T t = null;
        try {
            t = objectMapper.readValue(json, objClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}

