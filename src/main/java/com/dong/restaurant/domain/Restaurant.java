package com.dong.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "menuItems")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;

    @Column(name = "restaurant_name")
    @NotEmpty
    private String name;

    @Column(name = "restaurant_address")
    @NotEmpty
    private String address;

    @OneToMany(mappedBy = "restaurant")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MenuItem> menuItems=new ArrayList<>();

    public String getInformation() {
        return name + " in " + address;
    }

    public void addMenuItem(List<MenuItem> menuItems) {
       this.menuItems=new ArrayList<>(menuItems);
    }

    public void updateInformation(String name, String address) {
        this.name=name;
        this.address=address;
    }
}
