package com.dong.restaurant.service;

import com.dong.restaurant.domain.MenuItem;
import com.dong.restaurant.domain.Restaurant;
import com.dong.restaurant.repsoitory.MenuItemRepository;
import com.dong.restaurant.repsoitory.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {
    private MenuItemRepository menuItemRepository;
    private RestaurantRepository restaurantRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository,RestaurantRepository restaurantRepository){
        this.menuItemRepository=menuItemRepository;
        this.restaurantRepository=restaurantRepository;
    }

    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        for (MenuItem menuItem : menuItems) {
            menuItem.setRestaurant(restaurant);
            menuItemRepository.save(menuItem);
        }
    }
}