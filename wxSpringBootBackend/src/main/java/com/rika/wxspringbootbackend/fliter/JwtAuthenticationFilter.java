package com.rika.wxspringbootbackend.fliter;

import com.rika.wxspringbootbackend.entity.AuthUser;
import com.rika.wxspringbootbackend.entity.User;
import com.rika.wxspringbootbackend.service.UserService;
import com.rika.wxspringbootbackend.utils.RedisCache;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    RedisCache redisCache;
    @Autowired
    UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        var jwt = token.substring(7);
        var curUser = (User) redisCache.get(jwt);
        if (curUser == null) {
            log.info("token 已过期: " + token);
            filterChain.doFilter(request, response);
            return;
        }

        var authUser = new AuthUser(curUser);
        var upat = new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities());
        upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(upat);

        filterChain.doFilter(request, response);
    }
}
