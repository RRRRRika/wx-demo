package com.rika.wxspringbootbackend;

import com.rika.wxspringbootbackend.entity.User;
import com.rika.wxspringbootbackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DatabaseTest {

    @Autowired
    UserService userService;

    @Test
    public void testMysql() {
        var user = new User("rikarika");
        userService.addNormalUser(user);
    }
}
