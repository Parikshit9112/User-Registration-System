package com.springboot.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Country {
    
    @Id
    private Long c_id;
    @Column
    private String country_name;
    
    // @OneToMany(mappedBy = "country",cascade = CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval = true)
    // private List<State> states=new ArrayList<>();

}
