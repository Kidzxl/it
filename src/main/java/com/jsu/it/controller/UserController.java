package com.jsu.it.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jsu.it.entry.User;
import com.jsu.it.service.UserService;
import com.jsu.it.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/add")
    public Map<String,Object> addUser(User user){
        Map<String,Object> map = new HashMap<>();
        int i = userService.addUser(user);
        System.out.println(user);
        if(i==0){
            map.put("msg","增加用户失败");
        }else{
            map.put("msg","增加用户成功");
        }
        map.put("id",i);
        return map;
    }

    @RequestMapping("/hasUserName")
    public Map<String,Object> hasUserName(String username){
        Map<String,Object> map = new HashMap<>();
        boolean f = userService.hasUserName(username);
        map.put("hasUserName",f);
        return map;
    }

    @RequestMapping("/login")
    public Map<String,Object> login(User user) throws JsonProcessingException, NoSuchAlgorithmException, UnsupportedEncodingException {
        User u = userService.loginUser(user);
        Map<String,Object> map = new HashMap<>();
        if(u == null){
            map.put("code",404);
            map.put("token",null);
            map.put("msg","登录失败,没有此用户");
        }else{
            String k = "user:"+u.getUid()+System.currentTimeMillis();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            Base64.Encoder base64Encoder = Base64.getEncoder();
            String token =  base64Encoder.encodeToString(md5.digest(k.getBytes("utf-8")));
            String key = "user:"+u.getUid()+":token";
            redisUtil.set(key,token);
//            redisUtil.expire(key,60*60)
            map.put("code",200);
            map.put("token",token);
            map.put("msg","登录成功");
        }
        return map;
    }
}
