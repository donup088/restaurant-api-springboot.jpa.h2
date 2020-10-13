package com.dong.restaurant.controller;

import com.dong.restaurant.domain.Category;
import com.dong.restaurant.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> list() {
        return categoryService.getCategories();
    }
}
