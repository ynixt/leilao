package com.unkapps.leilao.service.user;

import com.unkapps.leilao.api.v1.dto.user.UserRegisterDto;
import com.unkapps.leilao.api.v1.exception.AppException;
import com.unkapps.leilao.api.v1.exception.dto.AppError;
import com.unkapps.leilao.api.v1.exception.dto.Code;
import com.unkapps.leilao.domain.User;
import com.unkapps.leilao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Transactional
    public User register(@NotNull UserRegisterDto userRegisterDto) {
        if (userRepository.existsByLogin(userRegisterDto.getLogin())) {
            throw new AppException(AppError.of(Code.USER_ALREADY_EXISTS));
        }

        User user = new User();
        user.setLogin(userRegisterDto.getLogin());
        user.setPassword(encoder.encode(userRegisterDto.getPassword()));
        user.setActive(true);

        return userRepository.save(user);
    }
}
