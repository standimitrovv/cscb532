package com.example.logisticcompany.models.dto;

import com.example.logisticcompany.exceptions.InvalidEmailCharacters;
import com.example.logisticcompany.exceptions.InvalidPhoneNumberCharacters;
import com.example.logisticcompany.util.Validation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class LogisticCompanyDto {

    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private BigDecimal income;

    public LogisticCompanyDto(String name, String address, String email, String phoneNumber) throws InvalidEmailCharacters, InvalidPhoneNumberCharacters {
        this.setName(name);
        this.setAddress(address);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.income = BigDecimal.valueOf(0);
    }

    private void setName(String name) {
        if (Validation.checkIfNullOrEmpty(name)) {
            throw new NullPointerException("Name should be a valid string!");
        }
        this.name = name;
    }

    private void setAddress(String address) {
        if (Validation.checkIfNullOrEmpty(address)) {
            throw new NullPointerException("Address should be a valid string!");
        }
        this.address = address;
    }

    private void setEmail(String email) throws InvalidEmailCharacters {
        if (Validation.checkIfNullOrEmpty(email)) {
            throw new NullPointerException("Email should be a valid string!");
        }

        if (Validation.validateEmail(email)) {
            this.email = email;
        }
    }

    private void setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberCharacters {
        if (Validation.checkIfNullOrEmpty(phoneNumber)) {
            throw new NullPointerException("Phone number should be a valid string!");
        }

        if (Validation.validatePhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        }
    }


    @Override
    public String toString() {
        return "LogisticCompanyDto{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
