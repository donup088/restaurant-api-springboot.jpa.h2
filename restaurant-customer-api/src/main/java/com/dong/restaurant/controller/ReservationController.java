package com.dong.restaurant.controller;

import com.dong.restaurant.domain.Reservation;
import com.dong.restaurant.service.ReservationsService;
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
public class ReservationController {

    private final ReservationsService reservationsService;

    @PostMapping("/restaurant/{restaurantId}/reservations")
    public ResponseEntity<?> create(
            @PathVariable("restaurantId") Long restaurantId,
            @Valid @RequestBody Reservation reservation,
            Authentication authentication)
            throws URISyntaxException {
        Claims claims = (Claims) authentication.getPrincipal();
        String name = claims.get("name", String.class);
        Long userId = claims.get("userId", Long.class);
        Reservation addReservation = reservationsService.addReservation(restaurantId, userId, name, reservation.getDate(), reservation.getTime(), reservation.getPartySize());

        return ResponseEntity.created(new URI("/restaurant/" + restaurantId + "/reservations/" + addReservation.getId())).body("{}");
    }
}
