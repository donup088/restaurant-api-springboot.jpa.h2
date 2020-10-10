package com.dong.restaurant.service;

import com.dong.restaurant.domain.MenuItem;
import com.dong.restaurant.domain.Restaurant;
import com.dong.restaurant.repsoitory.MenuItemRepository;
import com.dong.restaurant.repsoitory.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class RestaurantServiceTest {
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        restaurantService=new RestaurantService(restaurantRepository,menuItemRepository);

        List<Restaurant> restaurants=new ArrayList<>();
        restaurants.add(new Restaurant(11L,"밥집","서울"));

        MenuItem menuItem=new MenuItem("떡볶이");
        Restaurant restaurant=new Restaurant(1L,"분식집","서울");
        restaurant.addMenuItem(menuItem);

        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(1L)).willReturn(of(restaurant));
    }

    @Test
    public void getRestaurant(){
        Restaurant restaurant = restaurantService.getRestaurant(1L);
        MenuItem menuItem = restaurant.getMenuItems().get(0);
        assertEquals(restaurant.getId(),1L);
        assertEquals(menuItem.getName(),"떡볶이");
    }

    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        Restaurant restaurant = restaurants.get(0);
        assertEquals(restaurant.getId(),11L);
    }

    @Test
    public void addRestaurant(){
        Restaurant restaurant = new Restaurant("분식집", "서울");
        Restaurant saveRestaurant=new Restaurant(123L,"분식집","서울");

        given(restaurantRepository.save(any())).willReturn(saveRestaurant);

        Restaurant newRestaurant=restaurantService.addRestaurant(restaurant);

        assertEquals(newRestaurant.getId(),123L);
    }
}