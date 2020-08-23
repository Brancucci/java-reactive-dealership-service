package com.brancucci.ramblinwrecks.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private SearchRepository searchRepository;

    @Autowired
    public SearchService(SearchRepository searchRepository){
        this.searchRepository = searchRepository;
    }

    public HomeDto getLandingPage() {
        return HomeDto.builder()
                .vehicleColors(Arrays.stream(VehicleColors.values()).collect(Collectors.toSet()))
                .vehicleManufacturers(Arrays.stream(VehicleManufacturers.values()).collect(Collectors.toSet()))
                .vehicleTypes(Arrays.stream(VehicleTypes.values()).collect(Collectors.toSet()))
                .build();

    }
}
