package com.dong.restaurant.repsoitory;

import com.dong.restaurant.domain.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant,Long> {
    List<Restaurant> findAll();

    List<Restaurant> findAllByAddressContaining(String region);
}
