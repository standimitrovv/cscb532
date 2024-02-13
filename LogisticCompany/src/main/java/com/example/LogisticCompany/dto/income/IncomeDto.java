package com.example.LogisticCompany.dto.income;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IncomeDto {
    @NotNull(message = "The 'forDate' field cannot be null!")
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate forDate;

    @NotNull(message = "The 'amount' field cannot be null!")
    @Digits(integer = 10, fraction = 2, message = "The 'amount' field can have at most 12 digits before the fraction")
    private BigDecimal amount;

    @NotNull
    @Min(value = 1, message = "The 'companyId' field cannot be null!")
    private int companyId;
}
