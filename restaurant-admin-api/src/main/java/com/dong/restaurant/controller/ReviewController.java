package com.dong.restaurant.controller;

import com.dong.restaurant.domain.Review;
import com.dong.restaurant.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public List<Review> list(){
        return reviewService.getReviews();
    }
}
