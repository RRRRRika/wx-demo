package com.rika.wxspringbootbackend.service;

import com.rika.wxspringbootbackend.entity.User;
import com.rika.wxspringbootbackend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void addNormalUser(User user) {
        userMapper.addNormalUser(user);
    }

    public boolean hasUser(String uid) {
        return userMapper.getUser(uid) != null;
    }

    public User getUserByOpenID(String openID) {
        return userMapper.getUser(openID);
    }
}
