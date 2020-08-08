package com.jsu.it.controller;

import com.jsu.it.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@CrossOrigin
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private MailUtil mailUtil;

    @RequestMapping("/sendCode")
    public Map<String,Object> sendCode(String to){
        System.out.println(to);
        Random r = new Random();
        Integer code = 0;
        for (int i=0;i<4;i++){
            code = code*10 + r.nextInt(10);
        }
        Map<String,Object> map = new HashMap<>();
//        mailUtil.sendSimpleMail(to,"IT资讯俱乐部验证码",code.toString());
        map.put("code",code);
        return  map;
    }
}
