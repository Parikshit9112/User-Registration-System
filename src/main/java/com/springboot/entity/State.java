package com.springboot.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class State {
    
    @Id
    private Long s_id;
    
    @Column
    private String state_name;
    
    @ManyToOne
    private Country country;

    // @OneToMany(mappedBy = "state",cascade = CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval = true)
    // private List<City> cities=new ArrayList<>();

}
