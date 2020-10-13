package com.dong.restaurant.repsoitory;

import com.dong.restaurant.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
