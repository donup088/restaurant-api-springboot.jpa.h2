package com.dong.restaurant.service;

import com.dong.restaurant.domain.Region;
import com.dong.restaurant.repsoitory.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class RegionServiceTest {

    private RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        regionService=new RegionService(regionRepository);
    }

    @Test
    public void getRegions(){
        List<Region> regions=new ArrayList<>();
        regions.add(Region.builder().name("서울").build());

        given(regionRepository.findAll()).willReturn(regions);
        List<Region> getRegions = regionService.getRegions();

        Region region = getRegions.get(0);

        assertEquals(region.getName(),"서울");
    }

    @Test
    public void addRegion(){
        Region region = regionService.addRegion("서울");

        verify(regionRepository).save(any());

        assertEquals(region.getName(),"서울");
    }
}