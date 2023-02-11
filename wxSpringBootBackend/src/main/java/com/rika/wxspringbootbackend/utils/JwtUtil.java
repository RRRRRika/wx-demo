package com.rika.wxspringbootbackend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "SRzF6a724ZYAPg9EzGiBbJQDUBzcksl1";
    private final long defaultExpiration = 3600000;   // 1h

    public String generateToken(Map<String, Object> payload) {
        return JWT.create()
                .withSubject("auth_token")
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + defaultExpiration))
                .withIssuer("com.rika")
                .withClaim("random", Math.random() * 1919810)
                .withPayload(payload)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public boolean verify(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build()
                    .verify(token)
                    .getIssuer().equals("com.rika");
        } catch (Exception e) {
            return false;
        }
    }

}
