package com.dong.restaurant.service;

import com.dong.restaurant.domain.Category;
import com.dong.restaurant.domain.MenuItem;
import com.dong.restaurant.domain.Restaurant;
import com.dong.restaurant.exception.RestaurantNotFoundException;
import com.dong.restaurant.repsoitory.MenuItemRepository;
import com.dong.restaurant.repsoitory.RestaurantRepository;
import com.dong.restaurant.repsoitory.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
class RestaurantServiceTest {
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository,reviewRepository);
        Category category=Category.builder().id(1L).build();
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(Restaurant.builder()
                .id(11L)
                .category(category)
                .name("밥집")
                .address("서울")
                .build());

        List<MenuItem> menuItems = Arrays.asList(MenuItem.builder().name("떡볶이").build());
        Restaurant restaurant = Restaurant.builder().id(1L).name("분식집").address("서울").menuItems(menuItems).build();
        restaurant.addMenuItem(menuItems);

        given(restaurantRepository.findAllByAddressContainingAndCategoryId("Seoul",1L)).willReturn(restaurants);
        given(restaurantRepository.findById(1L)).willReturn(of(restaurant));
        given(menuItemRepository.findAllByRestaurantId(1L)).willReturn(menuItems);
    }

    @Test
    public void getRestaurant() {
        Restaurant restaurant = restaurantService.getRestaurant(1L);
        MenuItem menuItem = restaurant.getMenuItems().get(0);
        verify(menuItemRepository).findAllByRestaurantId(1L);
        verify(reviewRepository).findAllByRestaurantId(1L);
        assertEquals(restaurant.getId(), 1L);
        assertEquals(menuItem.getName(), "떡볶이");
    }

    @Test
    public void getRestaurantNotExisted() {
        assertThrows(RestaurantNotFoundException.class,
                () -> restaurantService.getRestaurant(222L));
    }


    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants("Seoul", 1L);

        Restaurant restaurant = restaurants.get(0);

        assertEquals(restaurant.getId(), 11L);
    }
}