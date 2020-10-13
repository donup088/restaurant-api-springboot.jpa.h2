package com.dong.restaurant.repsoitory;

import com.dong.restaurant.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category,Long> {

    List<Category> findAll();
}
