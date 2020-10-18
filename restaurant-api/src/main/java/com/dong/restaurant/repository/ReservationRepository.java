package com.dong.restaurant.repository;

import com.dong.restaurant.domain.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findAllByRestaurantId(Long restaurantId);
}
