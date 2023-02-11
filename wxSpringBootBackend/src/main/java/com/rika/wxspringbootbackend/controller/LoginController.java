package com.rika.wxspringbootbackend.controller;

import com.rika.wxspringbootbackend.entity.net.Resp;
import com.rika.wxspringbootbackend.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public ResponseEntity<Resp> login(@RequestParam("jsCode") String jsCode) {
        if (jsCode == null || jsCode.equals("")) {
            return ResponseEntity.badRequest().body(new Resp(400, "缺少 jsCode 或 jsCode 为空", null));
        }
        var token = loginService.login(jsCode);
        return ResponseEntity.ok().body(new Resp(200, "登录成功", Map.of("token", token)));
    }

}
