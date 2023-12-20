package com.example.logisticcompany.models.dto;

import com.example.logisticcompany.exceptions.InvalidEmailCharacters;
import com.example.logisticcompany.exceptions.InvalidPhoneNumberCharacters;
import com.example.logisticcompany.models.entity.Shipment;
import com.example.logisticcompany.util.Validation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class ClientDto {

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private Set<Shipment> receivedPackages;
    private Set<Shipment> sentPackages;
    private Set<Shipment> toBeReceived;

    public ClientDto(String firstName, String lastName, String address, String email, String phoneNumber) throws InvalidEmailCharacters, InvalidPhoneNumberCharacters {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.receivedPackages = new LinkedHashSet<>();
        this.sentPackages = new LinkedHashSet<>();
        this.toBeReceived = new LinkedHashSet<>();
    }

    private void setFirstName(String firstName) {
        if (Validation.checkIfNullOrEmpty(firstName)) {
            throw new NullPointerException("First name should be a valid string!");
        }
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        if (Validation.checkIfNullOrEmpty(lastName)) {
            throw new NullPointerException("Last name should be a valid string!");
        }
        this.lastName = lastName;
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
        return "ClientDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", receivedPackages=" + receivedPackages +
                ", sentPackages=" + sentPackages +
                ", toBeReceived=" + toBeReceived +
                '}';
    }
}
