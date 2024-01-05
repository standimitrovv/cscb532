package com.example.logisticcompany.service;

import com.example.logisticcompany.models.entity.Employee;
import com.example.logisticcompany.models.entity.Office;

import java.util.Set;

public interface OfficeService {
    boolean registerOffice(Office office);
    String showPhoneNumber(long id);
    String showAddress(long id);
    Set<Employee> showEmployees(long id);
    boolean changePhoneNumber(long id, String newPhoneNumber);
    boolean changeAddress(long id, String newAddress);
    boolean addNewEmployee(long id, Employee employee);
    boolean deleteOfficeInfo(long id);
}
