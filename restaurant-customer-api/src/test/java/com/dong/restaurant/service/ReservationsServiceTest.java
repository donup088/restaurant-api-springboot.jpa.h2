package com.dong.restaurant.service;

import com.dong.restaurant.domain.Reservation;
import com.dong.restaurant.repsoitory.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class ReservationsServiceTest {

    private ReservationsService reservationsService;
    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        reservationsService=new ReservationsService(reservationRepository);
    }

    @Test
    public void addReservation(){
        Long restaurantId = 123L;
        Long userId = 1L;
        String name = "dong";
        String date = "2021-1-1";
        String time = "20:00";
        Integer partySize = 10;

        given(reservationRepository.save(any())).will(invocation -> {
            return invocation.<Reservation>getArgument(0);
        });
        Reservation reservation = reservationsService.addReservation(restaurantId, userId, name, date, time, partySize);

        assertEquals(reservation.getName(),"dong");

        verify(reservationRepository).save(any());
    }
}