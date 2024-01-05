package com.example.logisticcompany.service.impl;

import com.example.logisticcompany.exceptions.*;
import com.example.logisticcompany.exceptions.NonExistingEmployee;
import com.example.logisticcompany.models.dto.EmployeeDto;
import com.example.logisticcompany.models.dto.ShipmentDto;
import com.example.logisticcompany.models.entity.Employee;
import com.example.logisticcompany.models.entity.Office;
import com.example.logisticcompany.models.entity.Shipment;
import com.example.logisticcompany.repository.EmployeeRepository;
import com.example.logisticcompany.repository.ShipmentRepository;
import com.example.logisticcompany.service.EmployeeService;
import com.example.logisticcompany.util.enums.EmployeeType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ShipmentRepository shipmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ShipmentRepository shipmentRepository) {
        this.employeeRepository = employeeRepository;
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public ResponseEntity<String> registerEmployee(EmployeeDto employeeDto) throws InvalidRegistration {
        if (employeeRepository.findEmployeeByEmail(employeeDto.getEmail()).isPresent()) {
            throw new InvalidRegistration("Client with this email already exists!");
        }

        ModelMapper modelMapper = new ModelMapper();
        Employee employee = modelMapper.map(employeeDto, Employee.class);

        String hashedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(hashedPassword);

        employeeRepository.saveAndFlush(employee);

        return ResponseEntity.ok("Successfully registered employee!");
    }

    @Override
    public String showEmployeeInfo(long id) throws NonExistingEmployee {
        if (searchEmployeeInRepository(id) != null) {
            Employee employee = searchEmployeeInRepository(id);
            StringBuilder info = new StringBuilder();

            info.append("Full name: ").append(employee.getFirstName()).append(employee.getLastName());
            info.append(System.lineSeparator());
            info.append("Email: ").append(employee.getEmail());
            info.append(System.lineSeparator());
            info.append("Phone number: ").append(employee.getPhoneNumber());
            info.append(System.lineSeparator());
            info.append("Role: ").append(employee.getRole());
            info.append(System.lineSeparator());
            info.append("Office: ").append(employee.getOffice().toString());
            info.append(System.lineSeparator());

            return info.toString();
        }

        throw new NonExistingEmployee("Unable to find an employee's information with this ID.");
    }

    @Override
    public ResponseEntity<String> changeFirstName(Employee employee, String name) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingEmployee, InvalidPasswordCharacters {
        if (searchEmployeeInRepository(employee.getId()) == null) {
            throw new NonExistingEmployee("Unable to find an employee with this ID to change his/her first name.");
        }

        // create a employee dto and validate the name
        EmployeeDto employeeDto = new EmployeeDto(name, employee.getLastName(), employee.getEmail(), employee.getPassword(), employee.getPhoneNumber(), employee.getRole(), employee.getOffice());

        // if the name is valid, the current entity employee will have a new changed name
        employee.setFirstName(employeeDto.getFirstName());

        // flush to DB
        employeeRepository.saveAndFlush(employee);

        return ResponseEntity.ok("Successfully changed first name");
    }

    @Override
    public ResponseEntity<String> changeLastName(Employee employee, String lastName) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingEmployee, InvalidPasswordCharacters {
        if (searchEmployeeInRepository(employee.getId()) == null) {
            throw new NonExistingEmployee("Unable to find an employee with this ID to change his/her last name.");
        }

        // create a employee dto and validate the name
        EmployeeDto employeeDto = new EmployeeDto(employee.getFirstName(), lastName, employee.getEmail(), employee.getPassword(), employee.getPhoneNumber(), employee.getRole(), employee.getOffice());

        // if the name is valid, the current entity employee will have a new changed name
        employee.setLastName(employeeDto.getLastName());

        // flush to DB
        employeeRepository.saveAndFlush(employee);

        return ResponseEntity.ok("Successfully changed last name");
    }

    @Override
    public ResponseEntity<String> changeEmail(Employee employee, String email) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingEmployee, InvalidPasswordCharacters {
        if (searchEmployeeInRepository(employee.getId()) == null) {
            throw new NonExistingEmployee("Unable to find an employee with this ID to change his/her email.");
        }

        // create a employee dto and validate the email
        EmployeeDto employeeDto = new EmployeeDto(employee.getFirstName(), employee.getLastName(), email, employee.getPassword(), employee.getPhoneNumber(), employee.getRole(), employee.getOffice());

        // if the email is valid, the current entity employee will have a new changed email
        employee.setEmail(employeeDto.getEmail());

        // flush to DB
        employeeRepository.saveAndFlush(employee);

        return ResponseEntity.ok("Successfully changed first email");
    }

    @Override
    public ResponseEntity<String> changePassword(Employee employee, String password) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingEmployee, InvalidPasswordCharacters {
        if (searchEmployeeInRepository(employee.getId()) == null) {
            throw new NonExistingEmployee("Unable to find an employee with this ID to change his/her password.");
        }

        // create a employee dto and validate the password
        EmployeeDto employeeDto = new EmployeeDto(employee.getFirstName(), employee.getLastName(), employee.getEmail(), password, employee.getPhoneNumber(), employee.getRole(), employee.getOffice());

        // if the password is valid, the current entity employee will have a new changed password
        employee.setPassword(employeeDto.getPassword());

        // flush to DB
        employeeRepository.saveAndFlush(employee);

        return ResponseEntity.ok("Successfully changed password");
    }

    @Override
    public ResponseEntity<String> changePhoneNumber(Employee employee, String phoneNumber) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingEmployee, InvalidPasswordCharacters {
        if (searchEmployeeInRepository(employee.getId()) == null) {
            throw new NonExistingEmployee("Unable to find an employee with this ID to change his/her password.");
        }

        // create a employee dto and validate the phone number
        EmployeeDto employeeDto = new EmployeeDto(employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPassword(), phoneNumber, employee.getRole(), employee.getOffice());

        // if the phone number is valid, the current entity employee will have a new changed phone number
        employee.setPhoneNumber(employeeDto.getPhoneNumber());

        // flush to DB
        employeeRepository.saveAndFlush(employee);

        return ResponseEntity.ok("Successfully changed phone number");
    }

    @Override
    public ResponseEntity<String> changeRole(Employee employee) throws NonExistingEmployee {
        if (searchEmployeeInRepository(employee.getId()) == null) {
            throw new NonExistingEmployee("Unable to find an employee with this ID to change his/her role.");
        }

        if (employee.getRole().equals(EmployeeType.COURIER)) {
            employee.setRole(EmployeeType.OFFICE_STAFF);
        } else {
            employee.setRole(EmployeeType.COURIER);
        }

        employeeRepository.saveAndFlush(employee);

        return ResponseEntity.ok("Successfully changed employee's role");
    }

    @Override
    public ResponseEntity<String> changeOffice(Employee employee, Office office) throws NonExistingEmployee {
        if (searchEmployeeInRepository(employee.getId()) == null) {
            throw new NonExistingEmployee("Unable to find an employee with this ID to change his/her office.");
        }

        employee.setOffice(office);
        employeeRepository.saveAndFlush(employee);

        return ResponseEntity.ok("Successfully changed employee's office");
    }

    @Override
    public List<Employee> listAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getAllEmployeesInOffice(long officeId) {
        return employeeRepository.findEmployeesByOffice(officeId);
    }

    @Override
    public ResponseEntity<String> registerShipment(ShipmentDto shipmentDto) {
        ModelMapper modelMapper = new ModelMapper();

        Shipment shipment = modelMapper.map(shipmentDto, Shipment.class);
        shipmentRepository.saveAndFlush(shipment);

        return ResponseEntity.ok("Successfully registered shipment");
    }

    @Override
    public ResponseEntity<String> changeShipmentStatus(Shipment shipment) {
        shipment.setDelivered(true);
        shipmentRepository.saveAndFlush(shipment);

        return ResponseEntity.ok("Successfully changed shipment status");
    }

    @Override
    public ResponseEntity<String> createAdmin(Employee employee) {
        employee.setAdmin(true);
        return ResponseEntity.ok("Successfully created admin");
    }

    @Override
    public ResponseEntity<String> changeAdmin(Employee admin, Employee employee) {
        // set current admin to false and set the new admin
        if (admin.isAdmin()) {
            admin.setAdmin(false);
            employee.setAdmin(true);

            employeeRepository.saveAndFlush(admin);
            employeeRepository.saveAndFlush(employee);

            return ResponseEntity.ok("Successfully changed admin");
        }

        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<String> deleteEmployeeInfo(long id) throws NonExistingEmployee {
        if (employeeRepository.findById(id).isPresent()) {
            Employee employee = employeeRepository.findById(id).get();
            employeeRepository.delete(employee);

            return ResponseEntity.ok("Successfully deleted employee info");
        }

        throw new NonExistingEmployee("Employee is non-existing.");
    }

    private Employee searchEmployeeInRepository(long id) {
        if (employeeRepository.findById(id).isPresent()) {
            return employeeRepository.findById(id).get();
        }
        return null;
    }
}
