package com.springboot.Form;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserForm {
    
    @NotBlank(message = "First Name is required")
    private String firstName;
    
    @NotBlank(message = "Last Name is required")
    private String lastName;
   
    // @Email(message = "Invalid Email Address")
    @NotBlank(message = "Email is required")
    private String email;

    // @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Min 6 Characters is required")
    private String password;

    @NotBlank(message="Select a gender option") 
    private String gender;
    
    // @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "^[0-9]{10}$",message = "Invalid Phone Number")
    private String phoneNumber;
    
    //Adddress
    @NotBlank(message="Addresss is required")
    private String streetAddress;
    private String streetAddress2;
    
    @NotBlank(message="City is required")
    private String city; 
    
    @NotBlank(message="State is required")
    private String state;
    
    // @NotBlank(message="Pincode is required")
    @Size(min=6,max=6,message = "Min 6 Digits are required")
    private String pincode;
    
    @NotBlank(message="Date Of Birth is required")
    private String birthDate;
    
    @NotBlank(message="Country is required")
    private String country;

}