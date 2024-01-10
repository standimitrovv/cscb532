package com.example.LogisticCompany.controller;

import com.example.LogisticCompany.dto.income.IncomeDto;
import com.example.LogisticCompany.dto.income.IncomeDtoResponse;
import com.example.LogisticCompany.dto.office.OfficeDto;
import com.example.LogisticCompany.dto.office.OfficeDtoResponse;
import com.example.LogisticCompany.service.implementation.IncomeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incomes")
public class IncomeController {
    private final IncomeServiceImpl incomeService;

    @Autowired
    public IncomeController(IncomeServiceImpl incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping
    public List<IncomeDtoResponse> getAllIncomes(){
        return this.incomeService.getAllIncomes();
    }

    @GetMapping("/{incomeId}")
    public IncomeDtoResponse getIncome(@PathVariable int incomeId){
        return this.incomeService.getIncome(incomeId);
    }

    @PostMapping
    public IncomeDtoResponse createNewIncome(@RequestBody @Valid IncomeDto incomeDto){
        return this.incomeService.createNewIncome(incomeDto);
    }

    @PutMapping("/{incomeId}")
    public IncomeDtoResponse updateIncome(
            @PathVariable int incomeId,
            @RequestBody @Valid IncomeDto incomeDto
    ){
        return this.incomeService.updateIncome(incomeId, incomeDto);
    }

    @DeleteMapping("/{incomeId}")
    public ResponseEntity<String> deleteIncome(@PathVariable int incomeId){
        this.incomeService.deleteIncome(incomeId);

        return ResponseEntity.ok("Income with id: '" + incomeId + "' was successfully deleted!");
    }
}
