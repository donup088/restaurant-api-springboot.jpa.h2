package com.dong.restaurant.controller;

import com.dong.restaurant.domain.User;
import com.dong.restaurant.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> list() {
        return userService.getUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User user) throws URISyntaxException {

        User createUser = userService.addUser(user.getEmail(), user.getName());

        return ResponseEntity.created(new URI("/users/"+createUser.getId())).body("{}");
    }

    @PatchMapping("/users/{userId}")
    public String update(@PathVariable("userId")Long userId,
                         @RequestBody User user){
        userService.updateUser(userId,user.getEmail(),user.getName(),user.getLevel());

        return "{}";
    }

    @DeleteMapping("/users/{userId}")
    public String delete(@PathVariable("userId")Long userId){
        userService.deactiveUser(userId);

        return "{}";
    }
}
