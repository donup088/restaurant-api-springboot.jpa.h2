package com.dong.restaurant.controller;

import com.dong.restaurant.domain.User;
import com.dong.restaurant.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User user) throws URISyntaxException {

        User registerUser = userService.registerUser(user.getEmail(), user.getName(), user.getPassword());

        return ResponseEntity.created(new URI("/users/"+ registerUser.getId())).body("{}");
    }
}
