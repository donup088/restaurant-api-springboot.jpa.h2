package com.dong.restaurant.controller;

import com.dong.restaurant.domain.User;
import com.dong.restaurant.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void list() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .email("test@test.com")
                .name("tester")
                .level(1)
                .build());

        given(userService.getUsers()).willReturn(users);

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("tester")));
    }

    @Test
    public void create() throws Exception {
        String email="test@test.com";
        String name="admin";
        User user=User.builder().email(email).name(name).build();
        given(userService.addUser(email,name)).willReturn(user);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@test.com\",\"name\":\"admin\"}"))
                .andExpect(status().isCreated());

        verify(userService).addUser(email,name);
    }


    @Test
    public void update() throws Exception {
        Long id=1L;
        String email="test@test.com";
        String name="admin";
        Integer level=100;
        User user=User.builder().email(email).name(name).level(level).build();

        given(userService.updateUser(id,email,name,level)).willReturn(user);

        mvc.perform(patch("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"email\":\"test@test.com\",\"name\":\"admin\",\"level\":100}"))
                .andExpect(status().isOk());

        verify(userService).updateUser(id,email,name,level);
    }
}