package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.entity.City;
import com.springboot.entity.State;
import com.springboot.service.LocationService;

@Controller
@RequestMapping("/location")
public class LocationController {
    
    @Autowired
    private LocationService locationService;

    @GetMapping
    public String getLocationPage(Model model) {
        model.addAttribute("countries", locationService.getAllCountries());
        return "location";
    }

    @ResponseBody
    @GetMapping("/states")
    public List<State> getStates(@RequestParam Long countryId) {
        return locationService.getStatesByCountry(countryId);
    }

    @ResponseBody
    @GetMapping("/cities")
    public List<City> getCities(@RequestParam Long stateId) {
        return locationService.getCitiesByState(stateId);
    }
}
