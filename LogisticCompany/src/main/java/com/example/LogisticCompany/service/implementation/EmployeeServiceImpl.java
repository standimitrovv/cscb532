package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.employee.EmployeeDto;
import com.example.LogisticCompany.dto.employee.EmployeeDtoResponse;
import com.example.LogisticCompany.model.employee.Employee;
import com.example.LogisticCompany.repository.EmployeeRepository;
import com.example.LogisticCompany.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Employee> employees = this.employeeRepository.findAll();

        return employees
                .stream()
                .map(e -> modelMapper.map(e, EmployeeDtoResponse.class))
                .collect(Collectors.toList());
    }

    public EmployeeDtoResponse getEmployee(int employeeId) {
        Employee e = this.employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return modelMapper.map(e, EmployeeDtoResponse.class);
    }

    public EmployeeDtoResponse createNewEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        this.employeeRepository.saveAndFlush(employee);

        return modelMapper.map(employee, EmployeeDtoResponse.class);
    }

    public EmployeeDtoResponse updateEmployee(int employeeId, EmployeeDto employeeDto) {
        Employee e = this.employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        e.setEmployeeType(employeeDto.getEmployeeType());
        e.setFullName(employeeDto.getFullName());
        e.setEmail(employeeDto.getEmail());
        e.setPhoneNumber(employeeDto.getPhoneNumber());

        Employee updatedEmployee = employeeRepository.save(e);

        return modelMapper.map(updatedEmployee, EmployeeDtoResponse.class);
    }

    public void deleteEmployee(int employeeId) {
        Employee e = this.employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        this.employeeRepository.delete(e);
    }
}
