package com.dong.restaurant.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name ="review_name")
    private String name;

    @NotNull
    @Column(name = "review_score")
    private Integer score;

    @NotEmpty
    @Column(name = "review_description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public void setRestaurant(Restaurant restaurant){
        this.restaurant=restaurant;
        restaurant.getReviews().add(this);
    }
}
