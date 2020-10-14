package com.dong.restaurant.service;

import com.dong.restaurant.domain.Restaurant;
import com.dong.restaurant.domain.Review;
import com.dong.restaurant.repsoitory.RestaurantRepository;
import com.dong.restaurant.repsoitory.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
class ReviewServiceTest {

    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        reviewService=new ReviewService(reviewRepository,restaurantRepository);
    }
    @Test
    public void addReview(){
        List<Review> reviews=new ArrayList<>();
        Restaurant restaurant=Restaurant
                .builder()
                .id(2L)
                .name("테스트집")
                .address("테스트주소")
                .reviews(reviews)
                .build();

        given(restaurantRepository.findById(2L)).willReturn(Optional.ofNullable(restaurant));

        reviewService.addReview(2L,"테스트집","굿",2);

        verify(reviewRepository).save(any());
    }
}