package com.example.logisticcompany.service;

import com.example.logisticcompany.models.entity.*;

import java.util.LinkedHashMap;
import java.util.Set;

public interface LogisticCompanyService {
    boolean registerCompany(LogisticCompany logisticCompany);
    String showName(long id);
    String showEmail(long id);
    String showAddress(long id);
    String showPhoneNumber(long id);
    Set<Office> showAllOffices(long id);
    Set<Employee> showAllEmployees(long id);
    Set<Shipment> showAllRegisteredPackages(long id);
    Set<Client> showAllRegisteredClients(long id);
    LinkedHashMap<Employee, Set<Shipment>> showAllRegisteredPackagesByEmployee(long id);
    Set<Shipment> showAllSentAndNotReceivedPackages(long id);
    boolean changeName(long id, String newName);
    boolean changeEmail(long id, String newEmail);
    boolean changeAddress(long id, String newAddress);
    boolean changePhoneNumber(long id, String newPhoneNumber);
    boolean addNewOffice(long id, Office office);
    boolean addNewEmployee(long id, Employee employee);
    boolean addNewRegisteredPackage(long id, Shipment shipment);
    boolean addNewClient(long id, Client client);
    boolean addNewRegisteredPackageByEmployee(long id, Shipment shipment, Employee employee);
    boolean addNewSentAndNotReceivedPackage(long id, Shipment shipment);
    boolean deleteCompanyInfo(long id);
}
