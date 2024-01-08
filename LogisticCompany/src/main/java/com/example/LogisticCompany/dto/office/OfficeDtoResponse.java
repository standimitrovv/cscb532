package com.example.LogisticCompany.dto.office;

import com.example.LogisticCompany.dto.employee.EmployeeDtoResponse;
import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDtoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfficeDtoResponse {
    private int id;

    private String address;

    private String phoneNumber;

    private Set<EmployeeDtoResponse> employees;

//    private Set<ShipmentDtoResponse> shipments;

    private LogisticCompanyDtoResponse logisticCompany;
}
