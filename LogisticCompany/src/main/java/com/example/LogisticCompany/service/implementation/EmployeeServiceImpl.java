package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.employee.EmployeeDto;
import com.example.LogisticCompany.dto.employee.EmployeeDtoResponse;
import com.example.LogisticCompany.model.employee.Employee;
import com.example.LogisticCompany.repository.EmployeeRepository;
import com.example.LogisticCompany.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<EmployeeDtoResponse> getAllEmployees() {
        return this.employeeRepository.findAllEmployees();
    }

    public EmployeeDtoResponse getEmployee(int employeeId) {
        return this.employeeRepository.findEmployeesById(employeeId).get();
    }

    public EmployeeDtoResponse createNewEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        this.employeeRepository.saveAndFlush(employee);

        return modelMapper.map(employee, EmployeeDtoResponse.class);
    }

    public EmployeeDtoResponse updateEmployee(int employeeId, EmployeeDto employeeDto) {
        Employee employee = this.employeeRepository.findById(employeeId).get();

        employee.setEmployeeType(employeeDto.getEmployeeType());
        employee.setFullName(employeeDto.getFullName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());

        Employee updatedEmployee = employeeRepository.save(employee);

        return modelMapper.map(updatedEmployee, EmployeeDtoResponse.class);

    }

    public void deleteEmployee(int employeeId) {
        if (employeeRepository.findById(employeeId).isPresent()) {
            Employee employee = employeeRepository.findById(employeeId).get();
            this.employeeRepository.delete(employee);
        }
    }
}
