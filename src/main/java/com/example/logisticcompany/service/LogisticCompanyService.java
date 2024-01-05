package com.example.logisticcompany.service;

import com.example.logisticcompany.exceptions.*;
import com.example.logisticcompany.models.entity.*;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface LogisticCompanyService {
    void createLogisticCompany() throws InvalidRegistration;
    String showCompanyInfo(LogisticCompany logisticCompany);
    ResponseEntity<String> changeName(String name) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters;
    ResponseEntity<String> changeEmail(String email) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters;
    ResponseEntity<String> changeAddress(String address) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters;
    ResponseEntity<String> changePhoneNumber(String number) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters;

    BigDecimal calculateTotalIncome();
}
