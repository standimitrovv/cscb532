package com.example.LogisticCompany.dto.person;

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
public abstract class PersonDto {

    @NotBlank(message = "The 'fullName' field cannot be blank!")
    @Size(min = 4, max = 45, message = "The 'fullName' field has to contain at least 4 and at most 45 characters!")
    private String fullName;

    @Email
    @NotBlank(message = "The 'email' field cannot be blank!")
    @Size(min = 5, max = 30, message = "The 'email' field has to contain at least 5 and at most 30 characters!")
    private String email;

    @NotBlank(message = "The 'phoneNumber' field cannot be blank!")
    @Size(min = 10, max = 10, message = "The 'phoneNumber' field must be 10 characters long!")
    private String phoneNumber;
}
