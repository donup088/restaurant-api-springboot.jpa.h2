package com.dong.restaurant.service;

import com.dong.restaurant.domain.Restaurant;
import com.dong.restaurant.domain.Review;
import com.dong.restaurant.repsoitory.RestaurantRepository;
import com.dong.restaurant.repsoitory.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private RestaurantRepository restaurantRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Review addReview(Long restaurantId, String name, String description, Integer score) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        Review review=Review.builder()
                .name(name)
                .description(description)
                .score(score)
                .build();

        review.setRestaurant(restaurant);

        return reviewRepository.save(review);
    }
}
