package com.example.LogisticCompany.service;

import com.example.LogisticCompany.dto.income.IncomeDto;
import com.example.LogisticCompany.dto.income.IncomeDtoResponse;

import java.util.List;

public interface IncomeService {
    List<IncomeDtoResponse> getAllIncomes();

    IncomeDtoResponse getIncome(int incomeId);

    IncomeDtoResponse createNewIncome(IncomeDto incomeDto);

    IncomeDtoResponse updateIncome(int incomeId, IncomeDto incomeDto);

    void deleteIncome(int incomeId);
}
