package com.dong.restaurant.controller;

import com.dong.restaurant.domain.Review;
import com.dong.restaurant.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

   @Test
    public void list() throws Exception {
       List<Review> reviews=new ArrayList<>();
       reviews.add(Review.builder().name("dong").description("good").score(4).build());

       given(reviewService.getReviews()).willReturn(reviews);

       mvc.perform(get("/reviews"))
               .andExpect(status().isOk())
               .andExpect(content().string(containsString("good")));
   }
}