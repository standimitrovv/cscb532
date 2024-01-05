package com.example.logisticcompany.service.impl;

import com.example.logisticcompany.exceptions.*;
import com.example.logisticcompany.models.dto.LogisticCompanyDto;
import com.example.logisticcompany.models.entity.*;
import com.example.logisticcompany.repository.*;
import com.example.logisticcompany.service.LogisticCompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LogisticCompanyServiceImpl implements LogisticCompanyService {

    // the logistic company is only ONE!
    private final LogisticCompanyRepository logisticCompanyRepository;
    private final ShipmentRepository shipmentRepository;
    private final LogisticCompany logisticCompany;

    public LogisticCompanyServiceImpl(LogisticCompanyRepository logisticCompanyRepository, ShipmentRepository shipmentRepository) {
        this.logisticCompanyRepository = logisticCompanyRepository;
        this.shipmentRepository = shipmentRepository;
        this.logisticCompany = null;
    }

    @Override
    public void createLogisticCompany() throws InvalidRegistration {
        if (logisticCompany != null) {
            throw new InvalidRegistration("A logistic company already exists!");
        }

        //TODO
    }

    @Override
    public String showCompanyInfo(LogisticCompany logisticCompany) {
        StringBuilder info = new StringBuilder();

        info.append("Company name: ").append(logisticCompany.getName());
        info.append(System.lineSeparator());
        info.append("Email: ").append(logisticCompany.getEmail());
        info.append(System.lineSeparator());
        info.append("Address: ").append(logisticCompany.getAddress());
        info.append(System.lineSeparator());
        info.append("Phone number: ").append(logisticCompany.getPhoneNumber());
        info.append(System.lineSeparator());

        return info.toString();
    }

    @Override
    public ResponseEntity<String> changeName(String name) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters {
        // create a company dto and validate the name
        LogisticCompanyDto logisticCompanyDto = new LogisticCompanyDto(name, logisticCompany.getAddress(), logisticCompany.getEmail(), logisticCompany.getPhoneNumber());

        // if the name is valid, the current entity company will have a new changed name
        logisticCompany.setName(logisticCompanyDto.getName());

        // flush to DB
        logisticCompanyRepository.saveAndFlush(logisticCompany);

        return ResponseEntity.ok("Successfully changed company name");
    }

    @Override
    public ResponseEntity<String> changeEmail(String email) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters {
        // create a company dto and validate the email
        LogisticCompanyDto logisticCompanyDto = new LogisticCompanyDto(logisticCompany.getName(), logisticCompany.getAddress(), email, logisticCompany.getPhoneNumber());

        // if the email is valid, the current entity company will have a new changed email
        logisticCompany.setEmail(logisticCompanyDto.getEmail());

        // flush to DB
        logisticCompanyRepository.saveAndFlush(logisticCompany);

        return ResponseEntity.ok("Successfully changed company email");
    }

    @Override
    public ResponseEntity<String> changeAddress(String address) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters {
        // create a company dto and validate the address
        LogisticCompanyDto logisticCompanyDto = new LogisticCompanyDto(logisticCompany.getName(), address, logisticCompany.getEmail(), logisticCompany.getPhoneNumber());

        // if the address is valid, the current entity company will have a new changed address
        logisticCompany.setAddress(logisticCompanyDto.getAddress());

        // flush to DB
        logisticCompanyRepository.saveAndFlush(logisticCompany);

        return ResponseEntity.ok("Successfully changed company address");
    }

    @Override
    public ResponseEntity<String> changePhoneNumber(String number) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters {
        // create a company dto and validate the number
        LogisticCompanyDto logisticCompanyDto = new LogisticCompanyDto(logisticCompany.getName(), logisticCompany.getAddress(), logisticCompany.getEmail(), number);

        // if the number is valid, the current entity company will have a new changed number
        logisticCompany.setName(logisticCompanyDto.getName());

        // flush to DB
        logisticCompanyRepository.saveAndFlush(logisticCompany);

        return ResponseEntity.ok("Successfully changed company number");
    }

    @Override
    public BigDecimal calculateTotalIncome() {
        BigDecimal income = BigDecimal.valueOf(0);

        List<Shipment> allShipments = shipmentRepository.findAll();
        List<BigDecimal> deliveryFees = allShipments.stream().map(Shipment::getDeliveryFee).toList();

        for (BigDecimal deliveryFee : deliveryFees) {
            income = income.add(deliveryFee);
        }

        logisticCompany.setIncome(income);
        logisticCompanyRepository.saveAndFlush(logisticCompany);

        return income;
    }
}
