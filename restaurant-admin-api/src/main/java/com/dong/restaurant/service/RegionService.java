package com.dong.restaurant.service;

import com.dong.restaurant.domain.Region;
import com.dong.restaurant.repsoitory.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getRegions() {
        return regionRepository.findAll();
    }

    public Region addRegion(String name) {
        Region region = Region.builder().name(name).build();
        regionRepository.save(region);

        return region;
    }
}
