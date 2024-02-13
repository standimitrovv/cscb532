package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.income.IncomeDto;
import com.example.LogisticCompany.dto.income.IncomeDtoResponse;
import com.example.LogisticCompany.dto.income.UpdateIncomeDto;
import com.example.LogisticCompany.model.Income;
import com.example.LogisticCompany.model.logisticCompany.LogisticCompany;
import com.example.LogisticCompany.repository.IncomeRepository;
import com.example.LogisticCompany.repository.LogisticCompanyRepository;
import com.example.LogisticCompany.service.IncomeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepository incomeRepository;
    private final LogisticCompanyRepository logisticCompanyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public IncomeServiceImpl(IncomeRepository incomeRepository, LogisticCompanyRepository logisticCompanyRepository) {
        this.incomeRepository = incomeRepository;
        this.logisticCompanyRepository = logisticCompanyRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<IncomeDtoResponse> getAllIncomes(LocalDate fromDate, LocalDate toDate) {
        if(fromDate == null && toDate == null){
            return mapIncomesToDtoResponseList(getAllCompanyIncomes());
        }

        return mapIncomesToDtoResponseList(
                getAllCompanyIncomes()
                        .filter(i -> {
                            LocalDate incomeDate = i.getForDate();

                            if(fromDate != null && toDate != null) {
                                return isIncomeDateAfterOrEqualFromDate(incomeDate, fromDate) && isIncomeDateBeforeOrEqualToDate(incomeDate, toDate);
                            }

                            if(toDate != null) {
                                return isIncomeDateBeforeOrEqualToDate(incomeDate, toDate);
                            }

                            return isIncomeDateAfterOrEqualFromDate(incomeDate, fromDate);
                        })
                );
    }

    public IncomeDtoResponse getIncome(int incomeId) {
        Income income = this.incomeRepository.findById(incomeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return modelMapper.map(income, IncomeDtoResponse.class);
    }

    public IncomeDtoResponse createNewIncome(IncomeDto incomeDto) {
        LogisticCompany company = this.logisticCompanyRepository.findById(incomeDto.getCompanyId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Income income = new Income();
        income.setAmount(incomeDto.getAmount());
        income.setForDate(incomeDto.getForDate());
        income.setLogisticCompany(company);

        this.incomeRepository.save(income);

        return modelMapper.map(income, IncomeDtoResponse.class);
    }

    public IncomeDtoResponse updateIncome(int incomeId, UpdateIncomeDto incomeDto) {
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

    private Stream<Income> getAllCompanyIncomes() {
        List<Income> incomes = this.incomeRepository.findAll();

        return incomes
                .stream()
                .filter(i -> i.getLogisticCompany().getId() == 1);
    }

    private List<IncomeDtoResponse> mapIncomesToDtoResponseList(Stream<Income> incomes){
        return incomes
                .map(i -> modelMapper.map(i, IncomeDtoResponse.class))
                .collect(Collectors.toList());
    }

    private boolean isIncomeDateAfterOrEqualFromDate(LocalDate incomeDate, LocalDate fromDate){
        return incomeDate.isAfter(fromDate) || incomeDate.isEqual(fromDate);
    }

    private boolean isIncomeDateBeforeOrEqualToDate(LocalDate incomeDate, LocalDate toDate){
        return incomeDate.isBefore(toDate) || incomeDate.isEqual(toDate);
    }
}
