package com.example.LogisticCompany.dto.office;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseOfficeDtoResponse {
    private int id;

    private String address;

    private String phoneNumber;
}
