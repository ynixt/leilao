package com.unkapps.leilao.service.auth;

import com.unkapps.leilao.api.dto.user.UserLoginDto;
import com.unkapps.leilao.api.exception.AppException;
import com.unkapps.leilao.api.exception.dto.AppError;
import com.unkapps.leilao.api.exception.dto.Code;
import com.unkapps.leilao.config.auth.MyUserDetails;
import com.unkapps.leilao.domain.User;
import com.unkapps.leilao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;


    @PersistenceContext
    private EntityManager entityManager;

    public String login(@NotNull UserLoginDto user) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword())
        );

        final MyUserDetails userDetails = (MyUserDetails) authenticate.getPrincipal();
        return jwtService.generateToken(userDetails);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(login);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + login));
        return user.map(MyUserDetails::new).get();
    }

    public User getCurrentUser() {
        try {
            Long userId = ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                    .getUserId();
            return entityManager.getReference(User.class, userId);
        } catch (
                EntityNotFoundException ex) {
            throw new AppException(AppError.of(Code.USER_NOT_FOUND));
        }
    }
}
