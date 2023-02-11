package com.rika.wxspringbootbackend.service;

import com.rika.wxspringbootbackend.entity.User;
import com.rika.wxspringbootbackend.entity.WxJsCodeResp;
import com.rika.wxspringbootbackend.utils.JwtUtil;
import com.rika.wxspringbootbackend.utils.RedisCache;
import com.rika.wxspringbootbackend.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LoginService {

    @Autowired
    WebUtil webUtil;
    @Autowired
    UserService userService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    RedisCache redisCache;

    public String login(String jsCode) {
        WxJsCodeResp resp = webUtil.getOpenID(jsCode);
        var openid = resp.getOpenid();
        var user = userService.getUserByOpenID(openid);
        if (user == null) {
            user = new User(openid);
            userService.addNormalUser(user);
        }
        var token = jwtUtil.generateToken(null);
        redisCache.set(token, user, 3600);
        return token;
    }

}
