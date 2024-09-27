package com.springboot.service;

import java.util.List;

import com.springboot.entity.City;
import com.springboot.entity.Country;
import com.springboot.entity.State;

public interface LocationService {
  
      public List<Country> getAllCountries();
      public List<State> getStatesByCountry(Long c_Id);
      public List<City> getCitiesByState(Long s_Id);
}
