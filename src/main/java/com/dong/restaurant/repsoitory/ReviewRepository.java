package com.dong.restaurant.repsoitory;

import com.dong.restaurant.domain.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review,Long> {
    List<Review> findAllByRestaurantId(Long restaurantId);
}
