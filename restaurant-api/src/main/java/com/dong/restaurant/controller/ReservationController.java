package com.dong.restaurant.controller;

import com.dong.restaurant.domain.Reservation;
import com.dong.restaurant.service.ReservationsService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationsService reservationsService;

    @GetMapping("/reservations")
    public List<Reservation> list(Authentication authentication){

        Claims claims= (Claims) authentication.getPrincipal();
        Long restaurantId = claims.get("restaurantId", Long.class);

        return reservationsService.getReservations(restaurantId);
    }
}
