package com.example.logisticcompany.service;

import com.example.logisticcompany.exceptions.InvalidEmailCharacters;
import com.example.logisticcompany.exceptions.InvalidPhoneNumberCharacters;
import com.example.logisticcompany.exceptions.NonExistingCompany;
import com.example.logisticcompany.models.entity.*;

import java.util.Map;
import java.util.Set;

public interface LogisticCompanyService {
    boolean registerCompany(LogisticCompany logisticCompany);
    String showName(long id) throws NonExistingCompany;
    String showEmail(long id) throws NonExistingCompany;
    String showAddress(long id) throws NonExistingCompany;
    String showPhoneNumber(long id) throws NonExistingCompany;
    Set<Office> showAllOffices(long id) throws NonExistingCompany;
    Set<Employee> showAllEmployees(long id) throws NonExistingCompany;
    Set<Shipment> showAllRegisteredPackages(long id) throws NonExistingCompany;
    Set<Client> showAllRegisteredClients(long id) throws NonExistingCompany;
    Map<Employee, Set<Shipment>> showAllRegisteredPackagesByEmployee(long id) throws NonExistingCompany;
    Set<Shipment> showAllSentAndNotReceivedPackages(long id) throws NonExistingCompany;
    boolean changeName(long id) throws NonExistingCompany, InvalidPhoneNumberCharacters, InvalidEmailCharacters;
    boolean changeEmail(long id) throws NonExistingCompany, InvalidPhoneNumberCharacters, InvalidEmailCharacters;
    boolean changeAddress(long id) throws NonExistingCompany, InvalidPhoneNumberCharacters, InvalidEmailCharacters;
    boolean changePhoneNumber(long id) throws NonExistingCompany, InvalidPhoneNumberCharacters, InvalidEmailCharacters;
    boolean addNewOffice(long id);
    boolean removeOffice(long id);
    boolean addNewEmployee(long id);
    boolean removeEmployee(long id);
    boolean addNewRegisteredPackage(long id);
    boolean addNewClient(long id);
    boolean removeClient(long id);
    boolean addNewRegisteredPackageByEmployee(long id);
    boolean addNewSentAndNotReceivedPackage(long id);
    boolean deleteCompanyInfo(long id);
}
