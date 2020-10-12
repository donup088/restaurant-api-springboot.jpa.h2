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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class MenuItemServiceTest {
    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;
    @Mock
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);//mock 초기화
        menuItemService = new MenuItemService(menuItemRepository, restaurantRepository);//mock을 초기화해야 menuItemRepository 객체가 들어감
    }

    @Test
    public void bulkUpdate() {
        List<MenuItem> menuItems2=new ArrayList<>();
        Restaurant restaurant = Restaurant
                .builder()
                .id(1L)
                .name("테스트")
                .address("주소")
                .menuItems(menuItems2)
                .build();
        given(restaurantRepository.findById(1L)).willReturn(Optional.ofNullable(restaurant));

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder().name("고기").build());
        menuItems.add(MenuItem.builder().id(13L).name("국").build());
        menuItems.add(MenuItem.builder().id(123L).destroy(true).build());
        menuItemService.bulkUpdate(1L, menuItems);

        verify(menuItemRepository, times(2)).save(any());
        verify(menuItemRepository, times(1)).deleteById(123L);

    }

    @Test
    public void getMenuItems(){
        List<MenuItem> menuItems=new ArrayList<>();
        menuItems.add(MenuItem.builder().name("밥").build());
        given(menuItemRepository.findAllByRestaurantId(123L)).willReturn(menuItems);

        List<MenuItem> getMenuItems=menuItemService.getMenuItems(123L);
        MenuItem menuItem=getMenuItems.get(0);
        assertEquals(menuItem.getName(),"밥");
    }
}