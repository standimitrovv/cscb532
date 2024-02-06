package com.example.LogisticCompany.dto.income;

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
public class BaseIncomeDtoResponse {
    private int id;

    private LocalDate forDate;

    private BigDecimal amount;
}
