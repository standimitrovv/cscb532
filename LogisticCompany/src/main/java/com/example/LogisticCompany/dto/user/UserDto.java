package com.example.LogisticCompany.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "The 'username' field cannot be blank!")
    @Size(min = 2, max = 15, message = "The 'username' field has to contain at least 2 and at most 15 characters!")
    private String username;

    @Email
    @NotBlank(message = "The 'email' field cannot be blank!")
    @Size(min = 5, max = 30, message = "The 'email' field has to contain at least 5 and at most 30 characters!")
    private String email;

    @NotBlank(message = "The 'password' field cannot be blank!")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "The 'password' field has to contain one lowercase, one uppercase letter, at least one digit, one special character, and must be at least 8 characters!"
    )
    private String password;
}
