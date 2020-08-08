package com.jsu.it.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jsu.it.entry.User;

public interface UserService {

    public int addUser(User user);

    public boolean hasUserName(String username);

    public User loginUser(User user) throws JsonProcessingException;
}
