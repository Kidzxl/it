package com.jsu.it.entry;

import java.util.HashMap;
import java.util.Map;

public class Msg {
    private Map<String,Object> map = new HashMap<>();

    public void setMsg(Object obj){
        map.put("msg",obj);
    }
    public void setCode(Object obj){
        map.put("code",obj);
    }
    public void setData(Object obj){
        map.put("data",obj);
    }

    public Map<String, Object> getMap() {
        return map;
    }

}
