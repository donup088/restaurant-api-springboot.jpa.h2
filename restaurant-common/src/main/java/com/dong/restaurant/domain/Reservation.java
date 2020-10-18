package com.dong.restaurant.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    @Column(name = "reservation_userid")
    private Long userId;

    @Column(name = "reservation_name")
    private String name;

    @NotEmpty
    @Column(name = "reservation_date")
    private String date;

    @NotEmpty
    @Column(name = "reservation_time")
    private String time;

    @NotNull
    @Column(name = "reservation_partysize")
    private Integer partySize;

    private Long restaurantId;
}
