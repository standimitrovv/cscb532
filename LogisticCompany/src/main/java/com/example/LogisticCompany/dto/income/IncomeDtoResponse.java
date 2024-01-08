package com.example.LogisticCompany.dto.income;

import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDtoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IncomeDtoResponse {
    private int id;

    private LocalDate forDate;

    private BigDecimal amount;

    private LogisticCompanyDtoResponse logisticCompany;
}
