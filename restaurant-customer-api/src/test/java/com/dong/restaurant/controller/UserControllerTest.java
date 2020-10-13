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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void create() throws Exception {
        String email="test@test.com";
        String name="dong";
        String password="test";

        User user=User.builder()
                .id(1L)
                .email(email)
                .password(password)
                .build();
        given(userService.registerUser(email,name,password)).willReturn(user);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@test.com\",\"name\":\"dong\",\"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/users/1"));

        verify(userService).registerUser(any(),any(),any());
    }
}