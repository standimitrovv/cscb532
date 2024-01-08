package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.income.IncomeDto;
import com.example.LogisticCompany.dto.income.IncomeDtoResponse;
import com.example.LogisticCompany.repository.IncomeRepository;
import com.example.LogisticCompany.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;

    @Autowired
    public IncomeServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public List<IncomeDtoResponse> getAllIncomes() {
        return null;
    }

    public IncomeDtoResponse getIncome(int incomeId) {
        return null;
    }

    public IncomeDtoResponse createNewIncome(IncomeDto incomeDto) {
        return null;
    }

    public IncomeDtoResponse updateIncome(int incomeId, IncomeDto incomeDto) {
        return null;
    }

    public void deleteIncome(int incomeId) {

    }
}
