package com.unkapps.leilao.service.auth.jwt;

import com.unkapps.leilao.api.v1.dto.LoginDto;
import com.unkapps.leilao.api.v1.dto.user.UserLoginDto;
import com.unkapps.leilao.api.v1.dto.user.UserRegisterDto;
import com.unkapps.leilao.config.auth.MyUserDetails;
import com.unkapps.leilao.domain.User;
import com.unkapps.leilao.service.auth.AuthService;
import com.unkapps.leilao.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class AuthJwtService extends AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    private String login(@NotNull String login, String password) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password)
        );

        final MyUserDetails userDetails = (MyUserDetails) authenticate.getPrincipal();
        return jwtService.generateToken(userDetails);
    }

    @Override
    public String login(@NotNull UserLoginDto user) {
        return login(user.getLogin(), user.getPassword());
    }

    @Override
    public String register(@NotNull UserRegisterDto userDto) {
        User user = userService.register(userDto);
        return login(user.getLogin(), userDto.getPassword());
    }

    public LoginDto renewToken() {
        MyUserDetails currentuserDetails = getCurrentUserDetails();
        return new LoginDto(jwtService.generateToken(currentuserDetails), currentuserDetails.getUsername());
    }
}
