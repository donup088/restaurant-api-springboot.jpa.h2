package com.dong.restaurant.controller;

import com.dong.restaurant.domain.User;
import com.dong.restaurant.exception.EmailNotExistedException;
import com.dong.restaurant.exception.PasswordWrongException;
import com.dong.restaurant.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SessionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void create() throws Exception {
        User mockUser = User.builder().password("ACCESSTOKEN").build();
        given(userService.authenticate("test@test.com", "test")).willReturn(mockUser);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@test.com\",\"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/session"))
                .andExpect(content().string("{\"accessToken\":\"ACCESSTOKE\"}"));

        verify(userService).authenticate("test@test.com", "test");
    }

    @Test
    public void 올바르지않은비밀번호세션생성실패() throws Exception {
        given(userService.authenticate("test@test.com", "1"))
                .willThrow(PasswordWrongException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@test.com\",\"password\":\"1\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate("test@test.com", "1");
    }

    @Test
    public void 이메일이없는경우세션생성실패() throws Exception {
        given(userService.authenticate("1@test.com", "test"))
                .willThrow(EmailNotExistedException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"1@test.com\",\"password\":\"test\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate("1@test.com", "test");
    }
}