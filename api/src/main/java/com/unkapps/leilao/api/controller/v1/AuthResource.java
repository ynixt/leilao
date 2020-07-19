package com.unkapps.leilao.api.controller.v1;

import com.unkapps.leilao.api.dto.user.UserLoginDto;
import com.unkapps.leilao.api.dto.user.UserRegisterDto;
import com.unkapps.leilao.service.auth.AuthService;
import com.unkapps.leilao.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthResource {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@Valid @RequestBody UserLoginDto user) {
        return authService.login(user);
    }

    @PostMapping("/register")
    public ResponseEntity<String> signUp(@Valid @RequestBody UserRegisterDto user) {
        userService.register(user);
        return ResponseEntity.ok("");
    }
}
