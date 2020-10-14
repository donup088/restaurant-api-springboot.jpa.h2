package com.dong.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

    @NotNull
    private Integer level;

    @NotEmpty
    private String password;

    public Boolean isAdmin() {
        return level >= 100;
    }

    public void updateUser(String email, String name, Integer level) {
        this.email = email;
        this.name = name;
        this.level = level;
    }

    public boolean isActive() {
        return level > 0;
    }

    public void deactivate() {
        this.level = 0;
    }

    public String getAccessToken() {
        if (password == null) {
            return "";
        }
        return password.substring(0, 10);
    }
}
