package com.springboot.Form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ForgotPasswordForm {

    @NotBlank(message = "Username required")
    private String username;
    
    @NotBlank(message = "New Password required")
    private String password;

    @NotBlank(message = "Re-enter New Password required")
    private String re_password;

}
