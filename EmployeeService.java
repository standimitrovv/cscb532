package com.example.logisticcompany.service;

import com.example.logisticcompany.models.entity.Employee;
import com.example.logisticcompany.models.entity.Office;
import com.example.logisticcompany.models.entity.Shipment;
import com.example.logisticcompany.util.EmployeeType;

import java.util.Set;

public interface EmployeeService {
    boolean registerEmployee(Employee employee);
    String showName(long id);
    String showEmail(long id);
    String showPhoneNumber(long id);
    EmployeeType showRole(long id);
    Office showOffice(long id);
    Set<Shipment> showAllRegisteredShipments(long id);
    boolean changeName(long id, String newName);
    boolean changeEmail(long id, String newEmail);
    boolean changePhoneNumber(long id, String newPhoneNumber);
    boolean changeRole(long id, EmployeeType newRole);
    boolean changeOffice(long id, Office office);
    boolean addNewRegisteredShipment(long id, Shipment shipment);
    boolean deleteEmployeeInfo(long id);
}
