package com.company.scorh.common;

import java.util.HashMap;
import java.util.Map;


public class SimpleCache {

    private static Map<String ,Object> map;

    public static void setAttribute(String key,Object val){
        if (map==null) map=new HashMap<>();
        map.put(key,val);
    }

    public static Object getAttribute(String key){
        return map.get(key);
    }

    public static void remove(String key){
        map.remove(key);
    }

    public static void clear(){
        map.clear();
    }
}
