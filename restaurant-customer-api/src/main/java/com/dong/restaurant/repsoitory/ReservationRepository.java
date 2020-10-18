package com.dong.restaurant.repsoitory;

import com.dong.restaurant.domain.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
