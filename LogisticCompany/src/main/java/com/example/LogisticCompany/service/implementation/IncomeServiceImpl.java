package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.income.IncomeDto;
import com.example.LogisticCompany.dto.income.IncomeDtoResponse;
import com.example.LogisticCompany.model.Income;
import com.example.LogisticCompany.repository.IncomeRepository;
import com.example.LogisticCompany.service.IncomeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public IncomeServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<IncomeDtoResponse> getAllIncomes() {
        List<Income> incomes = this.incomeRepository.findAll();

        return incomes
                .stream()
                .map(i -> modelMapper.map(i, IncomeDtoResponse.class))
                .collect(Collectors.toList());
    }

    public IncomeDtoResponse getIncome(int incomeId) {
        Income income = this.incomeRepository.findById(incomeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return modelMapper.map(income, IncomeDtoResponse.class);
    }

    public IncomeDtoResponse createNewIncome(IncomeDto incomeDto) {
        Income income = modelMapper.map(incomeDto, Income.class);
        this.incomeRepository.saveAndFlush(income);

        return modelMapper.map(income, IncomeDtoResponse.class);
    }

    public IncomeDtoResponse updateIncome(int incomeId, IncomeDto incomeDto) {
        Income income = this.incomeRepository.findById(incomeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        income.setAmount(incomeDto.getAmount());
        income.setForDate(incomeDto.getForDate());

        Income updatedIncome = incomeRepository.save(income);

        return modelMapper.map(updatedIncome, IncomeDtoResponse.class);
    }

    public void deleteIncome(int incomeId) {
        Income income = this.incomeRepository.findById(incomeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        this.incomeRepository.delete(income);
    }
}
