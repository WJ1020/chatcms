package com.example.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangShiXiang on 2017/5/9.
 * 用来转换json
 */
public class JsonUtil {

    final static ObjectMapper mapper=new ObjectMapper();


    public static String ObjectforJson(Object o){
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static JavaType getConnectionType(Class<?> connectionClass , Class<?>...emelentClass){

        return mapper.getTypeFactory().constructParametricType(connectionClass,emelentClass);
    }

    public static List JsonToList(Class c, String jsonString){
        if (jsonString==null)
            return null;
        try {
            return  mapper.readValue(jsonString,getConnectionType(ArrayList.class,c));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String toString(Object o){
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
