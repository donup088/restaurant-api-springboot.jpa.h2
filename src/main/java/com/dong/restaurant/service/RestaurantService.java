package com.dong.restaurant.service;

import com.dong.restaurant.domain.MenuItem;
import com.dong.restaurant.domain.Restaurant;
import com.dong.restaurant.repsoitory.MenuItemRepository;
import com.dong.restaurant.repsoitory.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public Restaurant getRestaurant(Long id){
        Restaurant restaurant=restaurantRepository.findById(id).orElse(null);
        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.addMenuItem(menuItems);

        return restaurant;
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Long id, String name, String address) {
        Restaurant restaurant = restaurantRepository.findById(id).get();
        restaurant.updateInformation(name,address);

        return restaurant;
    }
}
