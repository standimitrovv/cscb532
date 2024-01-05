package com.example.logisticcompany.service;

import com.example.logisticcompany.exceptions.InvalidPhoneNumberCharacters;
import com.example.logisticcompany.exceptions.NonExistingOffice;
import com.example.logisticcompany.models.entity.Employee;
import com.example.logisticcompany.models.entity.Office;

import java.util.Set;

public interface OfficeService {
    boolean registerOffice(Office office);
    String showPhoneNumber(long id) throws NonExistingOffice;
    String showAddress(long id) throws NonExistingOffice;
    Set<Employee> showEmployees(long id) throws NonExistingOffice;
    boolean changePhoneNumber(long id) throws NonExistingOffice, InvalidPhoneNumberCharacters;
    boolean changeAddress(long id) throws NonExistingOffice, InvalidPhoneNumberCharacters;
    boolean addNewEmployee(long id);
    boolean removeEmployee(long id);
    boolean deleteOfficeInfo(long id);
}
