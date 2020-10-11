package com.dong.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "restaurant")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menuitem_id")
    private Long id;

    @Column(name = "menuitem_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    public void setRestaurant(Restaurant restaurant){
        this.restaurant=restaurant;
        restaurant.getMenuItems().add(this);
    }
}
