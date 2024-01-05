package com.example.logisticcompany.models.dto;

import com.example.logisticcompany.exceptions.InvalidEmailCharacters;
import com.example.logisticcompany.exceptions.InvalidPasswordCharacters;
import com.example.logisticcompany.exceptions.InvalidPhoneNumberCharacters;
import com.example.logisticcompany.util.Validation;
import com.example.logisticcompany.util.enums.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClientDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private UserType userType;

    public ClientDto(String firstName, String lastName, String password, String email, String phoneNumber) throws InvalidEmailCharacters, InvalidPhoneNumberCharacters, InvalidPasswordCharacters {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPassword(password);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.userType = UserType.CLIENT;
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

    private void setPassword(String password) throws InvalidPasswordCharacters {
        if (Validation.checkIfNullOrEmpty(password)) {
            throw new NullPointerException("Password should be a valid string!");
        }

        if (Validation.validatePassword(password)) {
            this.password = password;
        }
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
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
