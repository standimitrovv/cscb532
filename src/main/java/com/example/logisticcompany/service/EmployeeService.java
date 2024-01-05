package com.example.logisticcompany.service;

import com.example.logisticcompany.exceptions.*;
import com.example.logisticcompany.models.dto.EmployeeDto;
import com.example.logisticcompany.models.dto.ShipmentDto;
import com.example.logisticcompany.models.entity.Employee;
import com.example.logisticcompany.models.entity.Office;
import com.example.logisticcompany.models.entity.Shipment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    ResponseEntity<String> registerEmployee(EmployeeDto employeeDto) throws InvalidRegistration;
    String showEmployeeInfo(long id) throws NonExistingEmployee;
    ResponseEntity<String> changeFirstName(Employee employee, String name) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingEmployee, InvalidPasswordCharacters;
    ResponseEntity<String> changeLastName(Employee employee, String lastName) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingEmployee, InvalidPasswordCharacters;
    ResponseEntity<String> changeEmail(Employee employee, String email) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingEmployee, InvalidPasswordCharacters;
    ResponseEntity<String> changePassword(Employee employee, String password) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingEmployee, InvalidPasswordCharacters;
    ResponseEntity<String> changePhoneNumber(Employee employee, String phoneNumber) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingEmployee, InvalidPasswordCharacters;
    ResponseEntity<String> changeRole(Employee employee) throws NonExistingEmployee;
    ResponseEntity<String> changeOffice(Employee employee, Office office) throws NonExistingEmployee;
    List<Employee> listAllEmployees();
    List<Employee> getAllEmployeesInOffice(long officeId);
    ResponseEntity<String> registerShipment(ShipmentDto shipmentDto);
    ResponseEntity<String> changeShipmentStatus(Shipment shipment) throws NonExistingShipment;

    ResponseEntity<String> createAdmin(Employee employee);

    ResponseEntity<String> changeAdmin(Employee admin, Employee employee);

    ResponseEntity<String> deleteEmployeeInfo(long id) throws NonExistingEmployee;
}
