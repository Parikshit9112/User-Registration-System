package com.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.City;
import com.springboot.entity.Country;
import com.springboot.entity.State;
import com.springboot.repositories.CityRepo;
import com.springboot.repositories.CountryRepo;
import com.springboot.repositories.StateRepo;
import com.springboot.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService{

     @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private StateRepo stateRepo;

    @Autowired
    private CityRepo cityRepo;

    public List<Country> getAllCountries() {
        return countryRepo.findAll();
    }

    public List<State> getStatesByCountry(Long countryId) {
        return stateRepo.findByCountry(countryRepo.findById(countryId).orElse(null));
    }

    public List<City> getCitiesByState(Long stateId) {
        return cityRepo.findByState(stateRepo.findById(stateId).orElse(null));
    }
    
}
