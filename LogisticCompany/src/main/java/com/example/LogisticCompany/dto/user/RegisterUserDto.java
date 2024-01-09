package com.example.LogisticCompany.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto extends UserDto {

    @Email
    @NotBlank(message = "The 'email' field cannot be blank!")
    @Size(min = 5, max = 30, message = "The 'email' field has to contain at least 5 and at most 30 characters!")
    private String email;
}
