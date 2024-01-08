package com.example.LogisticCompany.service;

import com.example.LogisticCompany.dto.employee.EmployeeDto;
import com.example.LogisticCompany.dto.employee.EmployeeDtoResponse;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDtoResponse> getAllEmployees();

    EmployeeDtoResponse getEmployee(int employeeId);

    EmployeeDtoResponse createNewEmployee(EmployeeDto employeeDto);

    EmployeeDtoResponse updateEmployee(int employeeId, EmployeeDto employeeDto);

    void deleteEmployee(int employeeId);
}
