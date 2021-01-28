package com.nongfu.kakax.basemain.controller;

import com.google.common.base.Strings;
import com.nongfu.kakax.basemain.auth.TokenService;
import com.nongfu.kakax.basemain.services.UserService;
import com.nongfu.kakax.basemain.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
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
        String token = tokenService.createToken(username);
        return Mono.just(token);
    }
}
