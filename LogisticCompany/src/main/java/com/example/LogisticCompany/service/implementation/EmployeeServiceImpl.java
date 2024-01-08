package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.EmployeeDto;
import com.example.LogisticCompany.dto.EmployeeDtoResponse;
import com.example.LogisticCompany.repository.EmployeeRepository;
import com.example.LogisticCompany.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRespository){
        this.employeeRepository = employeeRespository;
    }

    public EmployeeDtoResponse getAllEmployees() {
        return null;
    }

    public EmployeeDtoResponse getEmployee(int employeeId) {
        return null;
    }

    public EmployeeDtoResponse createNewEmployee(EmployeeDto employeeDto) {
        return null;
    }

    public EmployeeDtoResponse updateEmployee(int employeeId, EmployeeDto employeeDto) {
        return null;
    }

    public void deleteEmployee(int employeeId) {

    }
}
