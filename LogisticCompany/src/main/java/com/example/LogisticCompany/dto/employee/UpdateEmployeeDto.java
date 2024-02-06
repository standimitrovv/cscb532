package com.example.LogisticCompany.dto.employee;

import com.example.LogisticCompany.dto.person.PersonDto;
import com.example.LogisticCompany.model.employee.EmployeeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeDto extends PersonDto {

    @NotNull(message = "The 'employeeType' field cannot be null!")
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;
}
