package com.dong.restaurant.controller;

import com.dong.restaurant.domain.Reservation;
import com.dong.restaurant.service.ReservationsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReservationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReservationsService reservationService;

    @Test
    public void create() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJkb25nIn0.nFJeevg5yaVow0gINVZY-CJ7Zw6PHNhUqXHMzWsFX6c";
        Reservation mockReservation = Reservation.builder().id(123L).build();

        given(reservationService.addReservation(any(), any(), any(), any(), any(), any())).willReturn(mockReservation);

        mvc.perform(post("/restaurant/123/reservations")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"date\":\"2021-1-1\"," +
                        "\"time\": \"20:00\",\"partySize\":\"10\"}"))
                .andExpect(status().isCreated());

        Long restaurantId = 123L;
        Long userId = 1L;
        String name = "dong";
        String date = "2021-1-1";
        String time = "20:00";
        Integer partySize = 10;
        verify(reservationService).addReservation(restaurantId, userId, name, date, time, partySize);
    }
}