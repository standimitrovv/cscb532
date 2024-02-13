package com.example.LogisticCompany.service;

import com.example.LogisticCompany.dto.income.IncomeDto;
import com.example.LogisticCompany.dto.income.IncomeDtoResponse;
import com.example.LogisticCompany.dto.income.UpdateIncomeDto;

import java.time.LocalDate;
import java.util.List;

public interface IncomeService {
    List<IncomeDtoResponse> getAllIncomes(LocalDate fromDate, LocalDate toDate);

    IncomeDtoResponse getIncome(int incomeId);

    IncomeDtoResponse createNewIncome(IncomeDto incomeDto);

    IncomeDtoResponse updateIncome(int incomeId, UpdateIncomeDto incomeDto);

    void deleteIncome(int incomeId);
}
