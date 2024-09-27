package com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Country;
import com.springboot.entity.State;
import java.util.List;

public interface CountryRepo extends JpaRepository<Country,Long> {
    
}
