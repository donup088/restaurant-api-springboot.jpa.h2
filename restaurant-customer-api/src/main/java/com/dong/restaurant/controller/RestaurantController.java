package com.dong.restaurant.controller;

import com.dong.restaurant.domain.Restaurant;
import com.dong.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    //restaurant 전체조회 전체조회할때 메뉴와 리뷰도 다보이는 상황 dto 로 변환해서 안보이도록 해야함.
    @GetMapping("/restaurant")
    public List<Restaurant> list(
            @RequestParam("region") String region
            ,@RequestParam("category")Long categoryId) {
        return restaurantService.getRestaurants(region, categoryId);
    }

    //restaurant 단건조회(메뉴,리뷰 보이도록)
    @GetMapping("/restaurant/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        return restaurantService.getRestaurant(id);
    }
}
