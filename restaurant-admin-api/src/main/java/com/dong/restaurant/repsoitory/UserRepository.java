package com.dong.restaurant.repsoitory;

import com.dong.restaurant.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    List<User> findAll();
}
