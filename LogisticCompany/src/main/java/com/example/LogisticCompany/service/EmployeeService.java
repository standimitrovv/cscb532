package com.example.LogisticCompany.service;

import com.example.LogisticCompany.dto.employee.EmployeeDto;
import com.example.LogisticCompany.dto.employee.EmployeeDtoResponse;
import com.example.LogisticCompany.dto.employee.UpdateEmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDtoResponse> getAllEmployees();

    EmployeeDtoResponse getEmployee(int employeeId);

    EmployeeDtoResponse createNewEmployee(EmployeeDto employeeDto);

    void setUser(int employeeId, int userId);

    EmployeeDtoResponse updateEmployee(int employeeId, UpdateEmployeeDto employeeDto);

    void deleteEmployee(int employeeId);
}
