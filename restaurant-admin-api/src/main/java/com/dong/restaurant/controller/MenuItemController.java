package com.dong.restaurant.controller;

import com.dong.restaurant.domain.MenuItem;
import com.dong.restaurant.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuItemController {
    private final MenuItemService menuItemService;

    @PatchMapping("/restaurant/{restaurantId}/menuitems")
    public String bulkUpdate(@RequestBody List<MenuItem> menuItems, @PathVariable("restaurantId")Long restaurantId){
        menuItemService.bulkUpdate(restaurantId,menuItems);

        return "";
    }

    @GetMapping("/restaurant/{restaurantId}/menuitems")
    public List<MenuItem> list(@PathVariable("restaurantId")Long restaurantId){
        return menuItemService.getMenuItems(restaurantId);
    }
}
