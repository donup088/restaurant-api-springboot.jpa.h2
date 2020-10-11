package com.dong.restaurant.controller;

import com.dong.restaurant.domain.MenuItem;
import com.dong.restaurant.domain.Restaurant;
import com.dong.restaurant.exception.RestaurantNotFoundException;
import com.dong.restaurant.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurant"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":11")))
                .andExpect(content().string(containsString("\"name\":\"밥집\"")));
    }

    @Test
    public void 단건조회성공() throws Exception {
        Restaurant restaurant1 = Restaurant.builder()
                .id(123L)
                .name("밥집")
                .address("서울")
                .build();
        restaurant1.addMenuItem(Arrays.asList(MenuItem.builder().name("보리밥").build()));

        Restaurant restaurant2 = Restaurant.builder()
                .id(100L)
                .name("한식집")
                .address("서울")
                .build();
        given(restaurantService.getRestaurant(123L)).willReturn(restaurant1);
        given(restaurantService.getRestaurant(100L)).willReturn(restaurant2);

        mvc.perform(get("/restaurant/123"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":123")))
                .andExpect(content().string(containsString("\"name\":\"밥집\"")))
                .andExpect(content().string(containsString("보리밥")));

        mvc.perform(get("/restaurant/100"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":100")))
                .andExpect(content().string(containsString("\"name\":\"한식집\"")));
    }

    @Test
    public void 단건조회실패() throws Exception {
        given(restaurantService.getRestaurant(222L))
                .willThrow(new RestaurantNotFoundException(222L));

        mvc.perform(get("/restaurant/222"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }

    @Test
    public void 레스토랑생성() throws Exception {
        given(restaurantService.addRestaurant(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);

            return Restaurant.builder()
                    .id(1L)
                    .name(restaurant.getName())
                    .address(restaurant.getAddress())
                    .build();
        });

        mvc.perform(post("/restaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"중국집\",\"address\":\"서울\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurant/1"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any());
    }

    @Test
    public void 레스토랑생성에러처리() throws Exception {
        mvc.perform(post("/restaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"address\":\"\"}"))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void 레스토랑수정() throws Exception {
        mvc.perform(patch("/restaurant/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"양식집\",\"address\":\"부산\"}"))
                .andExpect(status().isOk());

        verify(restaurantService).updateRestaurant(123L, "양식집", "부산");
    }

    @Test
    public void 레스토랑수정에러처리() throws Exception {
        mvc.perform(patch("/restaurant/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"address\":\"\"}"))
                .andExpect(status().isBadRequest());
    }
}