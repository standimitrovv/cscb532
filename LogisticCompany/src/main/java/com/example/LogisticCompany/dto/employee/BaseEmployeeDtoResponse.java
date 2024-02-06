package com.example.LogisticCompany.dto.employee;

import com.example.LogisticCompany.dto.person.BasePersonDtoResponse;
import com.example.LogisticCompany.model.employee.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseEmployeeDtoResponse extends BasePersonDtoResponse {
    private EmployeeType employeeType;
}
