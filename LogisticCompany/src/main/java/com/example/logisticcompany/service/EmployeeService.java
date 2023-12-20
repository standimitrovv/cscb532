package com.example.logisticcompany.service;

import com.example.logisticcompany.exceptions.InvalidEmailCharacters;
import com.example.logisticcompany.exceptions.InvalidPhoneNumberCharacters;
import com.example.logisticcompany.exceptions.NonExistingClient;
import com.example.logisticcompany.exceptions.NonExistingEmployee;
import com.example.logisticcompany.models.entity.Employee;
import com.example.logisticcompany.models.entity.Office;
import com.example.logisticcompany.models.entity.Shipment;
import com.example.logisticcompany.util.enums.EmployeeType;

import java.io.IOException;
import java.util.Set;

public interface EmployeeService {
    boolean registerEmployee(Employee employee);
    String showName(long id) throws IOException, InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient, NonExistingEmployee;
    String showEmail(long id) throws NonExistingClient, NonExistingEmployee;
    String showPhoneNumber(long id) throws NonExistingClient, NonExistingEmployee;
    EmployeeType showRole(long id) throws NonExistingClient, NonExistingEmployee;
    Office showOffice(long id) throws NonExistingClient, NonExistingEmployee;
    Set<Shipment> showAllRegisteredShipments(long id) throws NonExistingClient, NonExistingEmployee;
    boolean changeFirstName(long id) throws IOException, InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient, NonExistingEmployee;
    boolean changeLastName(long id) throws IOException, InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient, NonExistingEmployee;
    boolean changeEmail(long id) throws NonExistingClient, IOException, InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingEmployee;
    boolean changePhoneNumber(long id) throws IOException, InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient, NonExistingEmployee;
    boolean changeRole(long id) throws IOException, InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient;
    boolean changeOffice(long id) throws IOException;
    boolean addNewRegisteredShipment(long id);
    boolean deleteEmployeeInfo(long id);
}
