package com.dong.restaurant.repsoitory;

import com.dong.restaurant.domain.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review,Long> {
}
