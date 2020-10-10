package com.dong.restaurant.controller;

import com.dong.restaurant.domain.Restaurant;
import com.dong.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    //restaurant 전체조회
    @GetMapping("/restaurant")
    public List<Restaurant> list() {
        return restaurantService.getRestaurants();
    }

    //restaurant 단건조회(메뉴 보이도록)
    @GetMapping("/restaurant/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {
        return restaurantService.getRestaurant(id);
    }

    //restaurant 생성
    @PostMapping("/restaurant")
    public ResponseEntity<?> create(@RequestBody Restaurant restaurant) throws URISyntaxException {
        Restaurant createRestaurant = restaurantService.addRestaurant(
                Restaurant.builder()
                        .name(restaurant.getName())
                        .address(restaurant.getAddress())
                        .build());

        URI uri = new URI("/restaurant/" + createRestaurant.getId());

        return ResponseEntity.created(uri).body("{}");
    }

    //restaurant 수정
    @PatchMapping("/restaurant/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody Restaurant restaurant) {
        restaurantService.updateRestaurant(id, restaurant.getName(), restaurant.getAddress());

        return "{}";
    }
}
