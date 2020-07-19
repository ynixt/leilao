package com.unkapps.leilao.service;

import com.unkapps.leilao.api.dto.user.UserRegisterDto;
import com.unkapps.leilao.api.exception.AppException;
import com.unkapps.leilao.api.exception.dto.Code;
import com.unkapps.leilao.api.exception.dto.CodeError;
import com.unkapps.leilao.domain.User;
import com.unkapps.leilao.repository.UserRepository;
import com.unkapps.leilao.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceRegisterTest {
    @Spy
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    PasswordEncoder encoder;

    private UserRegisterDto createUserDto() {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setLogin("test");
        userRegisterDto.setPassword("12asd");

        return userRegisterDto;
    }

    @Test
    public void whenLoginIsAvaliable() throws Exception {
        UserRegisterDto userDto = createUserDto();

        when(userRepository.existsByLogin("test")).thenReturn(false);
        when(encoder.encode(userDto.getPassword())).thenReturn("faked");
        when(userRepository.save(any(User.class))).thenReturn(new User());

        User user = userService.register(userDto);

        assertNotNull(user, "User must be not null");
    }

    @Test
    public void whenLoginIsNotAvaliable() throws Exception {
        UserRegisterDto userDto = createUserDto();

        when(userRepository.existsByLogin("test")).thenReturn(true);

        AppException exception = assertThrows(AppException.class, () -> {
            userService.register(userDto);
        });

        assertNotNull(exception.getError(), "Error must be not null");
        assertNotNull(exception.getError().getErrors(), "Errors must be not null");
        assertTrue(exception.getError().getErrors().size() == 1, "Errors must contain just one item");

        Object error = exception.getError().getErrors().get(0);

        assertTrue(error instanceof CodeError, "Error must be a CodeError");
        assertTrue(((CodeError) error).getCode() == Code.USER_ALREADY_EXISTS, "Error must contains correct code");
    }
}
