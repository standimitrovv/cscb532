package com.example.logisticcompany.models.dto;

import com.example.logisticcompany.exceptions.InvalidPasswordCharacters;
import com.example.logisticcompany.exceptions.InvalidRights;
import com.example.logisticcompany.exceptions.InvalidType;
import com.example.logisticcompany.models.entity.Client;
import com.example.logisticcompany.models.entity.Employee;
import com.example.logisticcompany.util.Validation;
import com.example.logisticcompany.util.enums.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserAuthenticationDto {

    private String userName;
    private String password;
    private UserType role;
    private Employee employee;
    private Client client;

    public UserAuthenticationDto(String userName, String password, UserType role, Employee employee, Client client) throws InvalidPasswordCharacters, InvalidType, InvalidRights {
        this.setUserName(userName);
        this.setPassword(password);
        this.setRole(role);
        this.setEmployee(employee);
        this.setClient(client);
    }

    private void setUserName(String userName) {
        if (Validation.checkIfNullOrEmpty(userName)) {
            throw new NullPointerException("Username should be a valid string!");
        }
        this.userName = userName;
    }

    private void setPassword(String password) throws InvalidPasswordCharacters {
        if (Validation.validatePassword(password)) {
            this.password = password;
        }
    }

    private void setRole(UserType role) throws InvalidType {
        if (!role.equals(UserType.CLIENT) && !role.equals(UserType.EMPLOYEE)) {
            throw new InvalidType("The selected role type is invalid!");
        }

        this.role = role;
    }

    private void setEmployee(Employee employee) throws InvalidRights {
        if (employee == null) {
            return;
        }

        if (this.role.equals(UserType.EMPLOYEE) && this.client == null) {
            this.employee = employee;
        }

        throw new InvalidRights("You have already chosen to be a client and do not have the rights as an employee!");
    }

    private void setClient(Client client) throws InvalidRights {
        if (client == null) {
            return;
        }

        if (this.role.equals(UserType.CLIENT) && this.employee == null) {
            this.client = client;
        }

        throw new InvalidRights("You have already chosen to be an employee role!");
    }
    public boolean checkIfOnlyUserOrEmployee() throws InvalidRights {
        if (this.employee != null && this.client != null) {
            throw new InvalidRights("You cannot register as both an employee and a client!");
        }

        return true;
    }

    @Override
    public String toString() {
        return "UserAuthenticationDto{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", employee=" + employee +
                ", client=" + client +
                '}';
    }
}
