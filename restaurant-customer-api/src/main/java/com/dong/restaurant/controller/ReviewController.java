package com.dong.restaurant.controller;

import com.dong.restaurant.domain.Review;
import com.dong.restaurant.service.ReviewService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
            @PathVariable("restaurantId") Long restaurantId,
            Authentication authentication
    ) throws URISyntaxException {
        Claims claims = (Claims) authentication.getPrincipal();
        String name = claims.get("name", String.class);
        Review createReview = reviewService.addReview(restaurantId, name, review.getDescription(), review.getScore());

        return ResponseEntity.created(new URI
                ("/com/dong/restaurant/" + restaurantId + "/review/" + createReview.getId()))
                .body("{}");
    }
}
