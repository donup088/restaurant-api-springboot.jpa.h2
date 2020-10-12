package com.dong.restaurant.repsoitory;

import com.dong.restaurant.domain.Region;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegionRepository extends CrudRepository<Region,Long> {
    List<Region> findAll();
}
