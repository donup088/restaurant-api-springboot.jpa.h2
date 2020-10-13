package com.dong.restaurant.controller;

import com.dong.restaurant.domain.Category;
import com.dong.restaurant.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> list(){
        return categoryService.getCategories();
    }

    @PostMapping("/categories")
    public ResponseEntity<?> create(@RequestBody Category category) throws URISyntaxException {
        Category createCategory=categoryService.addCategory(category.getName());

        return ResponseEntity.created(new URI("/categories/"+createCategory.getId())).body("{}");
    }
}
