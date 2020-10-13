package com.dong.restaurant.controller;

import com.dong.restaurant.domain.MenuItem;
import com.dong.restaurant.domain.Restaurant;
import com.dong.restaurant.domain.Review;
import com.dong.restaurant.exception.RestaurantNotFoundException;
import com.dong.restaurant.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    @Test
    public void 전체조회() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(
                Restaurant.builder()
                        .id(11L)
                        .name("밥집")
                        .address("서울")
                        .build());
        given(restaurantService.getRestaurants("Seoul")).willReturn(restaurants);

        mvc.perform(get("/restaurant?region=Seoul"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":11")))
                .andExpect(content().string(containsString("\"name\":\"밥집\"")));
    }

    @Test
    public void 단건조회성공() throws Exception {
        Restaurant restaurant = Restaurant.builder()
                .id(123L)
                .name("밥집")
                .address("서울")
                .build();
        Review review = Review.builder()
                .name("dong")
                .score(5)
                .description("good")
                .build();

        restaurant.addReview(Arrays.asList(review));
        restaurant.addMenuItem(Arrays.asList(MenuItem.builder().name("보리밥").build()));

        given(restaurantService.getRestaurant(123L)).willReturn(restaurant);

        mvc.perform(get("/restaurant/123"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":123")))
                .andExpect(content().string(containsString("\"name\":\"밥집\"")))
                .andExpect(content().string(containsString("보리밥")))
                .andExpect(content().string(containsString("good")));

    }

    @Test
    public void 단건조회실패() throws Exception {
        given(restaurantService.getRestaurant(222L))
                .willThrow(new RestaurantNotFoundException(222L));

        mvc.perform(get("/restaurant/222"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }
}