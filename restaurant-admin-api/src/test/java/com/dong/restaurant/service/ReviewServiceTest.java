package com.dong.restaurant.service;

import com.dong.restaurant.domain.Review;
import com.dong.restaurant.repsoitory.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class ReviewServiceTest {

    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        reviewService=new ReviewService(reviewRepository);
    }


    @Test
    public void getReviews(){
        List<Review> reviews=new ArrayList<>();
        reviews.add(Review.builder().name("dong").score(4).description("nice").build());
        given(reviewRepository.findAll()).willReturn(reviews);

        List<Review> getReviews = reviewService.getReviews();

        Review review = getReviews.get(0);

        assertEquals(review.getDescription(),"nice");
    }
}