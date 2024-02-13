package com.example.LogisticCompany.dto.user;

import com.example.LogisticCompany.dto.person.BasePersonDtoResponse;
import com.example.LogisticCompany.model.user.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDtoResponse {
    private int id;

    private String username;

    private String email;

    private UserType userType;

    private String token;

    private BasePersonDtoResponse person;
}
