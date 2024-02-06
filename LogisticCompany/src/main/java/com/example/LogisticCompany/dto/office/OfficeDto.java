package com.example.LogisticCompany.dto.office;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfficeDto {
    @NotBlank(message = "The 'address' field cannot be blank!")
    @Size(min = 5, max = 50, message = "The 'address' field has to contain at least 5 and at most 50 characters!")
    private String address;

    @NotBlank(message = "The 'phoneNumber' field cannot be blank!")
    @Size(min = 10, max = 10, message = "The 'phoneNumber' field must be 10 characters long!")
    private String phoneNumber;

    @Positive
    @Min(value = 1, message = "The 'companyId' field must be a positive integer")
    private int companyId;
}
