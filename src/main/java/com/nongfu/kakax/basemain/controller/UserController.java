package com.nongfu.kakax.basemain.controller;

import com.google.common.base.Strings;
import com.nongfu.kakax.basemain.auth.TokenService;
import com.nongfu.kakax.basemain.services.UserService;
import com.nongfu.kakax.basemain.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@RestController
@Slf4j
@RequestMapping(value = "/user", method = RequestMethod.GET)
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    @GetMapping("/list")
    public Mono<List<User>> getUsers() {
        List<User> list = userService.getUsers();
        return Mono.just(list);
    }

    @GetMapping("/login")
    public Mono<String> login(String username, String password) {
        if (Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
            return Mono.just("{code:20000, message: 'invalidate username or password!'}");
        }
        log.info(String.format(Locale.ROOT,"username:%s", username));
        String token = tokenService.createToken(username);
        return Mono.just(token);
    }

    @GetMapping("/rebuildToken")
    public Mono<String> rebuildToken() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        log.info(String.format(Locale.ROOT, "Get token: %s", token));
        if (Strings.isNullOrEmpty(token)) {
            Mono.just("{code:50000, message:'token is not allow empty!'}");
        }
        String subject = tokenService.getBody(token).getSubject();
        String newToken = tokenService.createToken(subject);
        return Mono.just(newToken);
    }
}
