package com.example.LogisticCompany.dto.employee;

import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDtoResponse;
import com.example.LogisticCompany.dto.office.OfficeDtoResponse;
import com.example.LogisticCompany.dto.person.PersonDtoResponse;
import com.example.LogisticCompany.dto.shipment.ShipmentDtoResponse;
import com.example.LogisticCompany.dto.user.UserDtoResponse;
import com.example.LogisticCompany.model.employee.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDtoResponse extends PersonDtoResponse {

    private EmployeeType employeeType;

    private Set<ShipmentDtoResponse> allCompanyShipments;

    private LogisticCompanyDtoResponse logisticCompany;

    private OfficeDtoResponse office;
}
