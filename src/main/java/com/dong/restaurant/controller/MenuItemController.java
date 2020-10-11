package com.dong.restaurant.controller;

import com.dong.restaurant.domain.MenuItem;
import com.dong.restaurant.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
