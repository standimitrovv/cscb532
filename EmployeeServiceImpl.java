package com.example.logisticcompany.service.impl;

import com.example.logisticcompany.models.entity.Employee;
import com.example.logisticcompany.models.entity.Office;
import com.example.logisticcompany.models.entity.Shipment;
import com.example.logisticcompany.repository.EmployeeRepository;
import com.example.logisticcompany.service.EmployeeService;
import com.example.logisticcompany.util.EmployeeType;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    //TODO
    @Override
    public boolean registerEmployee(Employee employee) {
        return false;
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
    public String showPhoneNumber(long id) {
        return null;
    }

    @Override
    public EmployeeType showRole(long id) {
        return null;
    }

    @Override
    public Office showOffice(long id) {
        return null;
    }

    @Override
    public Set<Shipment> showAllRegisteredShipments(long id) {
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
    public boolean changePhoneNumber(long id, String newPhoneNumber) {
        return false;
    }

    @Override
    public boolean changeRole(long id, EmployeeType newRole) {
        return false;
    }

    @Override
    public boolean changeOffice(long id, Office office) {
        return false;
    }

    @Override
    public boolean addNewRegisteredShipment(long id, Shipment shipment) {
        return false;
    }

    @Override
    public boolean deleteEmployeeInfo(long id) {
        return false;
    }
}
