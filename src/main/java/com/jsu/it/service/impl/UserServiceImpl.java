package com.jsu.it.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsu.it.dao.UserDao;
import com.jsu.it.entry.User;
import com.jsu.it.service.UserService;
import com.jsu.it.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public int addUser(User user) {
        userDao.addUser(user);
        return user.getUid();
    }

    @Override
    public boolean hasUserName(String username) {
        User user = userDao.hasUserName(username);
        if(user == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public User loginUser(User user) throws JsonProcessingException {
        String k = "user:"+user.getUsername();
        if(redisUtil.hasKey(k)){
            return null;
        }
        User u = userDao.hasUser(user);
        if (u == null){
            String key = "user:"+user.getUsername();
            redisUtil.set(key,null);
            redisUtil.expire(key,60);
        }
        return u;
    }
}
