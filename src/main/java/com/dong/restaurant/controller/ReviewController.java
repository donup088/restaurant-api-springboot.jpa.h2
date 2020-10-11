package com.dong.restaurant.controller;

import com.dong.restaurant.domain.Review;
import com.dong.restaurant.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/restaurant/{restaurantId}/review")
    public ResponseEntity<?> create(
            @Valid @RequestBody Review review,
            @PathVariable("restaurantId") Long restaurantId
    ) throws URISyntaxException {
        Review createReview = reviewService.addReview(restaurantId,review);

        return ResponseEntity.created(new URI
                ("/restaurant/" + restaurantId + "/review/" + createReview.getId()))
                .body("{}");
    }
}
