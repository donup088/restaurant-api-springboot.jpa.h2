package com.dong.restaurant.controller;

import com.dong.restaurant.domain.MenuItem;
import com.dong.restaurant.service.MenuItemService;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MenuItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MenuItemService menuItemService;

    @Test
    public void bulkUpdate() throws Exception {
        List<MenuItem> menuItems=new ArrayList<>();

        mvc.perform(patch("/restaurant/1/menuitems")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[]"))
                .andExpect(status().isOk());

        verify(menuItemService).bulkUpdate(1L,menuItems);
    }

    @Test
    public void list() throws Exception {
        List<MenuItem> menuItems=new ArrayList<>();
        menuItems.add(MenuItem.builder().name("밥").build());

        given(menuItemService.getMenuItems(123L)).willReturn(menuItems);

        mvc.perform(get("/restaurant/123/menuitems"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("밥")));
    }
}