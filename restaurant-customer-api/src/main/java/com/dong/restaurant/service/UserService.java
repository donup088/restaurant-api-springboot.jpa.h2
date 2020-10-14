package com.dong.restaurant.service;

import com.dong.restaurant.domain.User;
import com.dong.restaurant.exception.EmailExistedException;
import com.dong.restaurant.exception.EmailNotExistedException;
import com.dong.restaurant.exception.PasswordWrongException;
import com.dong.restaurant.repsoitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String email, String name, String password) {
        Optional<User> findUser = userRepository.findByEmail(email);
        if (findUser.isPresent()) {
            throw new EmailExistedException(email);
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .email(email)
                .name(name)
                .password(encodedPassword)
                .level(1)
                .build();

        return userRepository.save(user);
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotExistedException(email));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordWrongException();
        }
        return user;
    }
}
