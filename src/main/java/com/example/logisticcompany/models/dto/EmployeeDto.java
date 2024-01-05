package com.example.logisticcompany.models.dto;

import com.example.logisticcompany.exceptions.InvalidEmailCharacters;
import com.example.logisticcompany.exceptions.InvalidPasswordCharacters;
import com.example.logisticcompany.exceptions.InvalidPhoneNumberCharacters;
import com.example.logisticcompany.models.entity.Office;
import com.example.logisticcompany.util.enums.EmployeeType;
import com.example.logisticcompany.util.Validation;
import com.example.logisticcompany.util.enums.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EmployeeDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private EmployeeType role;
    private Office office;
    private UserType userType;

    public EmployeeDto(String firstName, String lastName, String email, String password, String phoneNumber, EmployeeType role, Office office) throws InvalidEmailCharacters, InvalidPhoneNumberCharacters, InvalidPasswordCharacters {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setPassword(password);
        this.setPhoneNumber(phoneNumber);
        this.setRole(role);
        this.setOffice(office);
        this.userType = UserType.EMPLOYEE;
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

    private void setEmail(String email) throws InvalidEmailCharacters {
        if (Validation.checkIfNullOrEmpty(email)) {
            throw new NullPointerException("Email should be a valid string!");
        }

        if (Validation.validateEmail(email)) {
            this.email = email;
        }
    }

    private void setPassword(String password) throws InvalidPasswordCharacters {
        if (Validation.checkIfNullOrEmpty(password)) {
            throw new NullPointerException("Password should be a valid string!");
        }

        if (Validation.validatePassword(password)) {
            this.password = password;
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

    private void setRole(EmployeeType role) {
        if (!role.equals(EmployeeType.COURIER) && !role.equals(EmployeeType.OFFICE_STAFF)) {
            throw new NullPointerException("The employee role is invalid!");
        }

        this.role = role;
    }

    private void setOffice(Office office) {
        if (office == null) {
            // cannot be left empty
            // TODO: more user friendly message
            throw new NullPointerException("The office should be a valid instance!");
        }
        this.office = office;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                ", office=" + office +
                '}';
    }
}
