package com.springboot.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User implements UserDetails{
    
    @Id
    private String userId;
    
    @Column(length=30,nullable = false)
    private String name;
    @Column(length=50,nullable = false)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;
 
    @Column(nullable = false)
    @Getter(AccessLevel.NONE)
    private String password;
    
    @Column(length=6,nullable = false)
    private String gender;

    @Column(length=10,nullable = false)
    
    private String phonenumber;

    @Column(length=250,nullable=false)
    private String address;

    @Column(length=10,nullable = false)
    private String birthDate;
    
    @Column(nullable=false)
    private String country;
    

    @ElementCollection(fetch=FetchType.EAGER)
    @Builder.Default
    private List<String> roleList=new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> roles=roleList.stream().map(role -> new  SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }

    @Override
    public String getPassword() {
       return this.password;
    }

    @Override
    public String getUsername() {
       return this.email;
    }

}
