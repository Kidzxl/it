package com.jsu.it;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsu.it.dao.UserDao;
import com.jsu.it.entry.User;
import com.jsu.it.util.MailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@SpringBootTest
class ItApplicationTests {

    @Autowired
    private MailUtil mailUtil;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
        mailUtil.sendSimpleMail("1726637374@qq.com","验证码","6333");
    }
    @Test
    void testUserController(){
        User user = new User();
        user.setUsername("root");
        userDao.addUser(user);
        System.out.println(user.getUid());
    }
    @Test
    public void testJson() throws JsonProcessingException {
        User user = new User();
        user.setUsername("root");
        user.setPassword("123456");
        // 序列化
        String s = objectMapper.writeValueAsString(user);
        System.out.println(s);
        //反序列化
        User u = objectMapper.readValue(s, User.class);
        System.out.println(u);
    }
    @Test
    public void testMd5() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Long startTs = System.currentTimeMillis();
        System.out.println(startTs);
        String key = "user:1"+startTs;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        Base64.Encoder base64Encoder = Base64.getEncoder();
        // 加密字符串
        String m =  base64Encoder.encodeToString(md5.digest(key.getBytes("utf-8")));
        System.out.println(m);
    }
}
