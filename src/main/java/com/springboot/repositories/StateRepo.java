package com.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Country;
import com.springboot.entity.State;

public interface StateRepo extends JpaRepository<State,Long> {
     List<State> findByCountry(Country country);
}
