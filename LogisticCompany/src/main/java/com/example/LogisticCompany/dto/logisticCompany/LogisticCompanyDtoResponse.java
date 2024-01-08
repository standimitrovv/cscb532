package com.example.LogisticCompany.dto.logisticCompany;

import com.example.LogisticCompany.dto.client.ClientDtoResponse;
import com.example.LogisticCompany.dto.employee.EmployeeDtoResponse;
import com.example.LogisticCompany.model.Client;
import com.example.LogisticCompany.model.Income;
import com.example.LogisticCompany.model.Office;
import com.example.LogisticCompany.model.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogisticCompanyDtoResponse {
    private int id;

    private String name;

    private String address;

    private String email;

    private String phoneNumber;

//    private Set<OfficeDtoResponse> offices;
//
//    private Set<IncomeDtoResponse> incomes;
//
    private Set<EmployeeDtoResponse> employees;

    private Set<ClientDtoResponse> clients;
}
