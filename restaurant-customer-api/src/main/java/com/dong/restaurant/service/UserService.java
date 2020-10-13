package com.dong.restaurant.service;

import com.dong.restaurant.domain.User;
import com.dong.restaurant.repsoitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String email, String name, String password) {
        User user=User.builder().email(email).name(name).password(password).build();

        return userRepository.save(user);
    }
}
