package com.nongfu.kakax.basemain.auth;

import com.nongfu.kakax.basemain.advice.JwtAuthException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtAuthFilter extends GenericFilterBean {
    @Autowired
    TokenService tokenService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestUri = request.getRequestURI();
        log.info(requestUri);
        if ("/user/login".equals(requestUri)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("Authorization");
        if (token == null) {
            notAuthResponse(response);
            return;
        }
        Claims body = tokenService.getBody(token);
        if (body == null || tokenService.isTokenExpirte(token)) {
            notAuthResponse(response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private void notAuthResponse(HttpServletResponse response) throws IOException {
        log.error("Authorization header is empty!");
        response.setStatus(403);
        response.getWriter().write("{code: 10000, message:'not login.'}");
    }
}
