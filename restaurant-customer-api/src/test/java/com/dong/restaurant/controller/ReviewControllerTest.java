package com.dong.restaurant.controller;

import com.dong.restaurant.domain.Review;
import com.dong.restaurant.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    public void 리뷰생성() throws Exception {
        given(reviewService.addReview(any(),any())).willReturn(
                Review.builder().id(12L).build()
        );

        mvc.perform(post("/restaurant/1/review")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"dong\",\"score\":3,\"description\":\"맛있다.\"}"))
                .andExpect(status().isCreated());

        verify(reviewService).addReview(any(),any());
    }

    @Test
    public void 리뷰생성에러처리() throws Exception {
        mvc.perform(post("/restaurant/1/review")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"score\":,\"description\":\".\"}"))
                .andExpect(status().isBadRequest());

        verify(reviewService,times(0)).addReview(any(),any());
    }
}