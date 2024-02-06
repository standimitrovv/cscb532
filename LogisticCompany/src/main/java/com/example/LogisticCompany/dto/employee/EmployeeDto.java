package com.example.LogisticCompany.dto.employee;

import com.example.LogisticCompany.dto.person.PersonDto;
import com.example.LogisticCompany.model.employee.EmployeeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto extends PersonDto {

    @NotNull(message = "The 'employeeType' field cannot be null!")
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    @NotNull
    @Positive
    @Min(value = 1, message = "The 'companyId' field must be a positive integer")
    private int companyId;

    @NotNull
    @Positive
    @Min(value = 1, message = "The 'officeId' field must be a positive integer")
    private int officeId;
}
