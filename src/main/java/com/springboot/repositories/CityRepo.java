package com.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.City;
import com.springboot.entity.State;

public interface CityRepo extends JpaRepository<City,Long> {
    List<City> findByState(State state);
}
