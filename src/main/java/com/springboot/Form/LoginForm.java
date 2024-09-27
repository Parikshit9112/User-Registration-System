package com.springboot.Form;

import jakarta.validation.constraints.NotNull;

public class LoginForm {
    
    @NotNull(message="Username required")
    private String userName;
    @NotNull(message="Password required")
    private String password;
}
