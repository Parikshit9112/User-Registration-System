package com.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter 
@Getter
@ToString
@Builder
public class UserDto {
    
    private String name;

    private String email;

    private String password;
    
    private String gender;

    private String phonenumber;

    private String address;

    private String birthDate;
    
    private String country;

}
