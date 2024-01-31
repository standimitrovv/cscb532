package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.employee.EmployeeDtoResponse;
import com.example.LogisticCompany.dto.income.IncomeDto;
import com.example.LogisticCompany.dto.income.IncomeDtoResponse;
import com.example.LogisticCompany.model.Income;
import com.example.LogisticCompany.model.employee.Employee;
import com.example.LogisticCompany.repository.IncomeRepository;
import com.example.LogisticCompany.service.IncomeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return this.incomeRepository.findAllIncomes();
    }

    public IncomeDtoResponse getIncome(int incomeId) {
        return this.incomeRepository.findIncomeById(incomeId).get();
    }

    public IncomeDtoResponse createNewIncome(IncomeDto incomeDto) {
        Income income = modelMapper.map(incomeDto, Income.class);
        this.incomeRepository.saveAndFlush(income);

        return modelMapper.map(income, IncomeDtoResponse.class);
    }

    public IncomeDtoResponse updateIncome(int incomeId, IncomeDto incomeDto) {
        Income income = this.incomeRepository.findById(incomeId).get();

        income.setAmount(incomeDto.getAmount());
        income.setForDate(incomeDto.getForDate());

        Income updatedIncome = incomeRepository.save(income);

        return modelMapper.map(updatedIncome, IncomeDtoResponse.class);
    }

    public void deleteIncome(int incomeId) {
        if (incomeRepository.findById(incomeId).isPresent()) {
            Income income = incomeRepository.findById(incomeId).get();
            this.incomeRepository.delete(income);
        }
    }
}
