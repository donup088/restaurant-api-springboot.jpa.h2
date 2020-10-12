package com.dong.restaurant.controller;

import com.dong.restaurant.domain.Region;
import com.dong.restaurant.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/regions")
    public List<Region> list(){
        return regionService.getRegions();
    }


    @PostMapping("/regions")
    public ResponseEntity<?> create(@RequestBody Region region) throws URISyntaxException {
        Region createRegion = regionService.addRegion(region.getName());

        return ResponseEntity.created(new URI("/regions/"+createRegion.getId())).body("{}");
    }
}
