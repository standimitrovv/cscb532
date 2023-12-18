package com.example.logisticcompany.service.impl;

import com.example.logisticcompany.models.entity.*;
import com.example.logisticcompany.repository.LogisticCompanyRepository;
import com.example.logisticcompany.service.LogisticCompanyService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Set;

@Service
public class LogisticCompanyServiceImpl implements LogisticCompanyService {

    private final LogisticCompanyRepository logisticCompanyRepository;

    public LogisticCompanyServiceImpl(LogisticCompanyRepository logisticCompanyRepository) {
        this.logisticCompanyRepository = logisticCompanyRepository;
    }

    @Override
    public boolean registerCompany(LogisticCompany logisticCompany) {
        // if one instance of the logistic company is already made, do not make more
        if (logisticCompanyRepository.count() == 0) {
            //TODO
        }
        throw new IllegalArgumentException("A logistic company is already created!");
    }

    @Override
    public String showName(long id) {
        return null;
    }

    @Override
    public String showEmail(long id) {
        return null;
    }

    @Override
    public String showAddress(long id) {
        return null;
    }

    @Override
    public String showPhoneNumber(long id) {
        return null;
    }

    @Override
    public Set<Office> showAllOffices(long id) {
        return null;
    }

    @Override
    public Set<Employee> showAllEmployees(long id) {
        return null;
    }

    @Override
    public Set<Shipment> showAllRegisteredPackages(long id) {
        return null;
    }

    @Override
    public Set<Client> showAllRegisteredClients(long id) {
        return null;
    }

    @Override
    public LinkedHashMap<Employee, Set<Shipment>> showAllRegisteredPackagesByEmployee(long id) {
        return null;
    }

    @Override
    public Set<Shipment> showAllSentAndNotReceivedPackages(long id) {
        return null;
    }

    @Override
    public boolean changeName(long id, String newName) {
        return false;
    }

    @Override
    public boolean changeEmail(long id, String newEmail) {
        return false;
    }

    @Override
    public boolean changeAddress(long id, String newAddress) {
        return false;
    }

    @Override
    public boolean changePhoneNumber(long id, String newPhoneNumber) {
        return false;
    }

    @Override
    public boolean addNewOffice(long id, Office office) {
        return false;
    }

    @Override
    public boolean addNewEmployee(long id, Employee employee) {
        return false;
    }

    @Override
    public boolean addNewRegisteredPackage(long id, Shipment shipment) {
        return false;
    }

    @Override
    public boolean addNewClient(long id, Client client) {
        return false;
    }

    @Override
    public boolean addNewRegisteredPackageByEmployee(long id, Shipment shipment, Employee employee) {
        return false;
    }

    @Override
    public boolean addNewSentAndNotReceivedPackage(long id, Shipment shipment) {
        return false;
    }

    @Override
    public boolean deleteCompanyInfo(long id) {
        return false;
    }
}
