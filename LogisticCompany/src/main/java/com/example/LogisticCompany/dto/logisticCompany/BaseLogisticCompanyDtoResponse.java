package com.example.LogisticCompany.dto.logisticCompany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseLogisticCompanyDtoResponse {
    private int id;

    private String name;

    private String address;

    private String email;

    private String phoneNumber;
}
