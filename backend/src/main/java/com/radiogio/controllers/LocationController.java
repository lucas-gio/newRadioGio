package com.radiogio.controllers;

import com.radiogio.models.Location;
import com.radiogio.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/continents")
    public List<String> getContinents() {
        return locationRepository.findDistinctContinents();
    }

    @GetMapping("/countries")
    public List<String> getCountries(@RequestParam String continent) {
        return locationRepository.findCountriesByContinent(continent);
    }

    @GetMapping("/states")
    public List<String> getStates(@RequestParam String country) {
        return locationRepository.findStatesByCountry(country);
    }
}
