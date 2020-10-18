package com.dong.restaurant.service;

import com.dong.restaurant.domain.User;
import com.dong.restaurant.exception.EmailNotExistedException;
import com.dong.restaurant.exception.PasswordWrongException;
import com.dong.restaurant.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository, passwordEncoder);
    }


    @Test
    public void authenticate성공() {
        String email = "test@test.com";
        String password = "test";
        User mockUser = User.builder().email(email).build();
        given(userRepository.findByEmail(email)).willReturn(ofNullable(mockUser));
        given(passwordEncoder.matches(any(), any())).willReturn(true);
        User user = userService.authenticate(email, password);

        assertEquals(user.getEmail(), email);
    }

    @Test
    public void authenticate이메일없음실패() {
        String email = "1@test.com";
        String password = "test";

        given(userRepository.findByEmail(email)).willReturn(empty());


        assertThrows(EmailNotExistedException.class,
                () -> userService.authenticate(email, password));
    }

    @Test
    public void authenticate비밀번호다름실패() {
        String email = "test@test.com";
        String password = "1";

        User mockUser = User.builder().email(email).build();
        given(userRepository.findByEmail(email)).willReturn(ofNullable(mockUser));
        given(passwordEncoder.matches(any(), any())).willReturn(false);

        assertThrows(PasswordWrongException.class,
                () -> userService.authenticate(email, password));
    }

}