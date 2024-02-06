package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.employee.EmployeeDto;
import com.example.LogisticCompany.dto.employee.EmployeeDtoResponse;
import com.example.LogisticCompany.dto.employee.UpdateEmployeeDto;
import com.example.LogisticCompany.model.Client;
import com.example.LogisticCompany.model.Office;
import com.example.LogisticCompany.model.employee.Employee;
import com.example.LogisticCompany.model.logisticCompany.LogisticCompany;
import com.example.LogisticCompany.model.user.User;
import com.example.LogisticCompany.model.user.UserType;
import com.example.LogisticCompany.repository.EmployeeRepository;
import com.example.LogisticCompany.repository.LogisticCompanyRepository;
import com.example.LogisticCompany.repository.OfficeRepository;
import com.example.LogisticCompany.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final OfficeRepository officeRepository;
    private final LogisticCompanyRepository logisticCompanyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, UserRepository userRepository, OfficeRepository officeRepository, LogisticCompanyRepository logisticCompanyRepository){
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.officeRepository = officeRepository;
        this.logisticCompanyRepository = logisticCompanyRepository;
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
        Employee employee = new Employee();
        employee.setEmployeeType(employeeDto.getEmployeeType());
        employee.setEmail(employeeDto.getEmail());
        employee.setFullName(employeeDto.getFullName());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());

        Office office = this.officeRepository.findById(employeeDto.getOfficeId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        LogisticCompany company = this.logisticCompanyRepository.findById(employeeDto.getCompanyId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        employee.setOffice(office);
        employee.setLogisticCompany(company);

        this.employeeRepository.save(employee);

        return modelMapper.map(employee, EmployeeDtoResponse.class);
    }

    public void setUser(int employeeId, int userId) {
        Employee employee = this.employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(user.getClient() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        user.setUserType(UserType.EMPLOYEE);

        employee.setUser(user);
        this.employeeRepository.save(employee);
    }

    public EmployeeDtoResponse updateEmployee(int employeeId, UpdateEmployeeDto employeeDto) {
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
