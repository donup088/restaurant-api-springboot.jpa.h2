package com.dong.restaurant.service;

import com.dong.restaurant.repsoitory.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userService=new UserService(userRepository);
    }
    @Test
    public void registerUser(){
        String email="test@test.com";
        String name="dong";
        String password="test";

        userService.registerUser(email,name,password);

        verify(userRepository).save(any());
    }
}