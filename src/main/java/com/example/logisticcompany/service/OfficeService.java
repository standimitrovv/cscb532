package com.example.logisticcompany.service;

import com.example.logisticcompany.exceptions.InvalidPhoneNumberCharacters;
import com.example.logisticcompany.exceptions.InvalidRegistration;
import com.example.logisticcompany.exceptions.NonExistingOffice;
import com.example.logisticcompany.models.dto.OfficeDto;
import com.example.logisticcompany.models.entity.Employee;
import com.example.logisticcompany.models.entity.Office;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface OfficeService {
    ResponseEntity<String> registerOffice(OfficeDto officeDto) throws InvalidRegistration;
    String showOfficeInfo(long id) throws NonExistingOffice;
    ResponseEntity<String> changePhoneNumber(Office office, String phoneNumber) throws InvalidPhoneNumberCharacters, NonExistingOffice;
    ResponseEntity<String> changeAddress(Office office, String address) throws NonExistingOffice, InvalidPhoneNumberCharacters;
    List<Office> showAllOffices();
    ResponseEntity<String> deleteOfficeInfo(long id) throws NonExistingOffice;
}
