package com.example.logisticcompany.models.dto;

import com.example.logisticcompany.exceptions.InvalidPhoneNumberCharacters;
import com.example.logisticcompany.models.entity.Employee;
import com.example.logisticcompany.util.Validation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
public class OfficeDto {

    private String address;
    private String phoneNumber;
    private Set<Employee> employees;

    public OfficeDto(String address, String phoneNumber) throws InvalidPhoneNumberCharacters {
        this.setAddress(address);
        this.setPhoneNumber(phoneNumber);
        this.employees = new LinkedHashSet<>();
    }

    private void setAddress(String address) {
        if (Validation.checkIfNullOrEmpty(address)) {
            throw new NullPointerException("Address should be a valid string!");
        }
        this.address = address;
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
        return "OfficeDto{" +
                "address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", employees=" + employees +
                '}';
    }
}
