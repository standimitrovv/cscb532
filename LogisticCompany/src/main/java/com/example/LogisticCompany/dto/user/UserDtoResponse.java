package com.example.LogisticCompany.dto.user;

import com.example.LogisticCompany.dto.client.BaseClientDtoResponse;
import com.example.LogisticCompany.dto.client.ClientDtoResponse;
import com.example.LogisticCompany.dto.employee.BaseEmployeeDtoResponse;
import com.example.LogisticCompany.dto.employee.EmployeeDtoResponse;
import com.example.LogisticCompany.model.Client;
import com.example.LogisticCompany.model.employee.Employee;
import com.example.LogisticCompany.model.user.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoResponse {
    private int id;

    private String username;

    private String email;

    private UserType userType;

    private BaseEmployeeDtoResponse employee;

    private BaseClientDtoResponse client;
}
