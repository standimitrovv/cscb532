package com.example.LogisticCompany.service;

import com.example.LogisticCompany.dto.EmployeeDto;
import com.example.LogisticCompany.dto.EmployeeDtoResponse;

public interface EmployeeService {

    EmployeeDtoResponse getAllEmployees();

    EmployeeDtoResponse getEmployee(int employeeId);

    EmployeeDtoResponse createNewEmployee(EmployeeDto employeeDto);

    EmployeeDtoResponse updateEmployee(int employeeId, EmployeeDto employeeDto);

    void deleteEmployee(int employeeId);
}
