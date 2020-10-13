package com.dong.restaurant.service;

import com.dong.restaurant.domain.User;
import com.dong.restaurant.repsoitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User addUser(String email, String name) {
        User user=User.builder().name(name).email(email).build();

        return userRepository.save(user);
    }

    public User updateUser(Long id, String email, String name, Integer level) {
        User user = userRepository.findById(id).get();
        user.updateUser(email,name,level);

        return user;
    }
}
