package com.dong.restaurant.service;

import com.dong.restaurant.domain.User;
import com.dong.restaurant.exception.EmailExistedException;
import com.dong.restaurant.repsoitory.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void registerUser() {
        String email = "test@test.com";
        String name = "dong";
        String password = "test";

        userService.registerUser(email, name, password);

        verify(userRepository).save(any());
    }

    @Test
    public void 이메일중복() {
        String email = "test@test.com";
        String name = "dong";
        String password = "test";
        User user = User.builder().build();
        given(userRepository.findByEmail(email)).willReturn(Optional.ofNullable(user));

        assertThrows(EmailExistedException.class,
                () -> userService.registerUser(email, name, password));

        verify(userRepository, never()).save(any());
    }
}