package com.example.logisticcompany.service.impl;

import com.example.logisticcompany.models.entity.Employee;
import com.example.logisticcompany.models.entity.Office;
import com.example.logisticcompany.repository.OfficeRepository;
import com.example.logisticcompany.service.OfficeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;

    public OfficeServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }


    //TODO
    @Override
    public boolean registerOffice(Office office) {
        return false;
    }

    @Override
    public String showPhoneNumber(long id) {
        return null;
    }

    @Override
    public String showAddress(long id) {
        return null;
    }

    @Override
    public Set<Employee> showEmployees(long id) {
        return null;
    }

    @Override
    public boolean changePhoneNumber(long id, String newPhoneNumber) {
        return false;
    }

    @Override
    public boolean changeAddress(long id, String newAddress) {
        return false;
    }

    @Override
    public boolean addNewEmployee(long id, Employee employee) {
        return false;
    }

    @Override
    public boolean deleteOfficeInfo(long id) {
        return false;
    }
}
