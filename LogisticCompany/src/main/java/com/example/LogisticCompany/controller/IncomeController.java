package com.example.LogisticCompany.controller;

import com.example.LogisticCompany.dto.income.IncomeDto;
import com.example.LogisticCompany.dto.income.IncomeDtoResponse;
import com.example.LogisticCompany.dto.income.UpdateIncomeDto;
import com.example.LogisticCompany.service.implementation.IncomeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/incomes")
public class IncomeController {
    private final IncomeServiceImpl incomeService;

    @Autowired
    public IncomeController(IncomeServiceImpl incomeService) {
        this.incomeService = incomeService;
    }

    /*
    * @param fromDate - the date we want to filter the incomes by/from.
    *        If the 'filterType' param is set to "BT", 'fromDate' is the starting date which we want to filter from.
    *        Otherwise, it's the date we want to filter by.
    * @param toDate - the date we want to filter the incomes to.
    *        Only applies if the 'filterType' param is set to "BT".
    *        Otherwise, you could skip checking it for being defined.
    * */
    @GetMapping
    public List<IncomeDtoResponse> getAllIncomes(
            @RequestParam(name = "fromDate", required = false) LocalDate fromDate,
            @RequestParam(name = "toDate", required = false) LocalDate toDate
    ){
        return this.incomeService.getAllIncomes(fromDate, toDate);
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
            @RequestBody @Valid UpdateIncomeDto incomeDto
    ){
        return this.incomeService.updateIncome(incomeId, incomeDto);
    }

    @DeleteMapping("/{incomeId}")
    public ResponseEntity<String> deleteIncome(@PathVariable int incomeId){
        this.incomeService.deleteIncome(incomeId);

        return ResponseEntity.ok("Income with id: '" + incomeId + "' was successfully deleted!");
    }
}
