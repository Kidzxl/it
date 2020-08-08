package com.jsu.it.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
//@CrossOrigin
public class HelloController {

    @RequestMapping("/hello")
    public Map<String,Object> hello(int id){
        Map<String,Object > map = new HashMap<>();
        map.put("code",200);
        map.put("id",id);
        return map;
    }
}
