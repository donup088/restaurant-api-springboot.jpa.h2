package com.dong.restaurant.controller;

import com.dong.restaurant.domain.MenuItem;
import com.dong.restaurant.domain.Restaurant;
import com.dong.restaurant.service.RestaurantService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        restaurants.add(new Restaurant(11L, "밥집", "서울"));
        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurant"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":11")))
                .andExpect(content().string(containsString("\"name\":\"밥집\"")));
    }

    @Test
    public void 단건조회() throws Exception {
        Restaurant restaurant1 = new Restaurant(123L, "밥집", "서울");
        restaurant1.addMenuItem(new MenuItem("밥"));
        Restaurant restaurant2 = new Restaurant(100L, "한식집", "서울");
        given(restaurantService.getRestaurant(123L)).willReturn(restaurant1);
        given(restaurantService.getRestaurant(100L)).willReturn(restaurant2);

        mvc.perform(get("/restaurant/123"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":123")))
                .andExpect(content().string(containsString("\"name\":\"밥집\"")))
                .andExpect(content().string(containsString("밥")));

        mvc.perform(get("/restaurant/100"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":100")))
                .andExpect(content().string(containsString("\"name\":\"한식집\"")));
    }

    @Test
    public void 레스토랑생성() throws Exception {
        given(restaurantService.addRestaurant(any())).will(invocation -> {
            Restaurant restaurant=invocation.getArgument(0);

            return new Restaurant(1L,restaurant.getName(),restaurant.getAddress());
        });

        mvc.perform(post("/restaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"중국집\",\"address\":\"서울\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurant/1"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any());
    }
}