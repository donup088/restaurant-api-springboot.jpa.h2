package com.dong.restaurant.service;

import com.dong.restaurant.domain.User;
import com.dong.restaurant.repsoitory.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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
    public void getUsers() {
        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .email("test@test.com")
                .name("tester")
                .level(1)
                .build());
        given(userRepository.findAll()).willReturn(users);

        List<User> getUsers = userService.getUsers();
        assertEquals(getUsers.get(0).getName(), "tester");
    }

    @Test
    public void addUser() {
        String email="test@test.com";
        String name="tester";
        User user = User.builder()
                .email(email)
                .name(name)
                .level(1)
                .build();

        given(userRepository.save(any())).willReturn(user);

        User createUser = userService.addUser(email, name);

        assertEquals(createUser.getName(),"tester");
    }

    @Test
    public void updateUser(){
        Long id=1L;
        String email="test@test.com";
        String name="123";
        Integer level=100;
        User user=User.builder().id(id).email(email).name("admin").level(level).build();

        given(userRepository.findById(1L)).willReturn(Optional.ofNullable(user));
        User updateUser = userService.updateUser(id, email, name, level);

        verify(userRepository).findById(id);

        assertEquals(updateUser.getName(),"123");
        assertEquals(user.isAdmin(),true);
    }

    @Test
    public void deactiveUser(){
        Long id=1L;
        String email="test@test.com";
        String name="admin";
        Integer level=100;
        User user=User.builder().id(id).email(email).name(name).level(level).build();

        given(userRepository.findById(1L)).willReturn(Optional.ofNullable(user));

        User deactiveUser = userService.deactiveUser(1L);

        verify(userRepository).findById(1L);

        assertEquals(deactiveUser.isAdmin(),false);
        assertEquals(deactiveUser.isActive(),false);
    }
}