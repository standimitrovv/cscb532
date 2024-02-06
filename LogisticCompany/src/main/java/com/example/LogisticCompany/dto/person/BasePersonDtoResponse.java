package com.example.LogisticCompany.dto.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasePersonDtoResponse {
    private int id;

    private String fullName;

    private String email;

    private String phoneNumber;
}
