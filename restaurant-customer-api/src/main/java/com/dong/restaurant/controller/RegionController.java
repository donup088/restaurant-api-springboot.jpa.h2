package com.dong.restaurant.controller;

import com.dong.restaurant.domain.Region;
import com.dong.restaurant.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/regions")
    public List<Region> list(){
        return regionService.getRegions();
    }

}
