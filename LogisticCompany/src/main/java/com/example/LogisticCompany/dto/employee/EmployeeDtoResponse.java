package com.example.LogisticCompany.dto.employee;

import com.example.LogisticCompany.dto.person.PersonDtoResponse;
import com.example.LogisticCompany.model.employee.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDtoResponse extends PersonDtoResponse {

    private EmployeeType employeeType;

//    private UserDtoResponse user;

//    private Set<ShipmentDtoResponse> shipments;

//    private LogisticCompanyDtoResponse logisticCompany;

//    private OfficeDtoResponse office;
}
