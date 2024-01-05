package com.example.logisticcompany.models.dto;

import com.example.logisticcompany.util.enums.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phoneNumber;
    private UserType userType;

    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserDto(String firstName, String lastName, String password, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
