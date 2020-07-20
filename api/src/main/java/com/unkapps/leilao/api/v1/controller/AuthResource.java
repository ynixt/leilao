package com.unkapps.leilao.api.v1.controller;

import com.unkapps.leilao.api.v1.dto.LoginDto;
import com.unkapps.leilao.api.v1.dto.user.UserLoginDto;
import com.unkapps.leilao.api.v1.dto.user.UserRegisterDto;
import com.unkapps.leilao.service.auth.AuthService;
import com.unkapps.leilao.service.auth.jwt.AuthJwtService;
import com.unkapps.leilao.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authenticated"),
            @ApiResponse(responseCode = "400", description = "Dto doesn't respect the business role"),
            @ApiResponse(responseCode = "403", description = "Login or password incorrect"),
    })
    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<LoginDto> login(@Valid @RequestBody UserLoginDto user) {
        String token = authService.login(user);

        return ResponseEntity.ok(new LoginDto(token, user.getLogin()));
    }

    @ApiOperation(value = "Renew a token", authorizations = {@Authorization(value = "Bearer")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New token"),
            @ApiResponse(responseCode = "403", description = "Token expired or invalid"),
    })
    @PostMapping(value = "/renewToken", produces = "application/json")
    public ResponseEntity<LoginDto> renewToken() {
        return ResponseEntity.ok(((AuthJwtService) authService).renewToken());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered"),
            @ApiResponse(responseCode = "400", description = "Dto doesn't respect the business role"),
    })
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<Void> signUp(@Valid @RequestBody UserRegisterDto user) {
        userService.register(user);
        return ResponseEntity.ok().build();
    }
}
