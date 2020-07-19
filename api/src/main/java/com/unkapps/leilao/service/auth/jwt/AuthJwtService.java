package com.unkapps.leilao.service.auth.jwt;

import com.unkapps.leilao.api.v1.dto.user.UserLoginDto;
import com.unkapps.leilao.config.auth.MyUserDetails;
import com.unkapps.leilao.service.auth.AuthService;
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

    @Override
    public String login(@NotNull UserLoginDto user) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword())
        );

        final MyUserDetails userDetails = (MyUserDetails) authenticate.getPrincipal();
        return jwtService.generateToken(userDetails);
    }
}
