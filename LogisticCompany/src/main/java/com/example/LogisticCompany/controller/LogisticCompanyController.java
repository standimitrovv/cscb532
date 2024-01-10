package com.example.LogisticCompany.controller;

import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDto;
import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDtoResponse;
import com.example.LogisticCompany.model.logisticCompany.FilterType;
import com.example.LogisticCompany.service.implementation.LogisticCompanyServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/logisticCompanies")
public class LogisticCompanyController {

    private final LogisticCompanyServiceImpl logisticCompanyService;

    @Autowired
    public LogisticCompanyController(LogisticCompanyServiceImpl logisticCompanyService) {
        this.logisticCompanyService = logisticCompanyService;
    }

    @GetMapping
    public List<LogisticCompanyDtoResponse> getAllLogisticCompanies(){
        return this.logisticCompanyService.getAllLogisticCompanies();
    };

    /*
    * Gets a logistic company by id and allows the user to get a specific income(s) of that company from X to Y date
    *
    * @param filterType - the different filter types we can use to select income dates.
    * @param fromDate - the date we want to filter the incomes by/from.
    *        If the 'filterType' param is set to "BT", 'fromDate' is the starting date which we want to filter from.
    *        Otherwise, it's the date we want to filter by.
    * @param toDate - the date we want to filter the incomes to.
    *        Only applies if the 'filterType' param is set to "BT".
    *        Otherwise, you could skip checking it for being defined.
    * */
    @GetMapping("/{companyId}")
    public LogisticCompanyDtoResponse getLogisticCompany(
            @PathVariable int companyId,
            @RequestParam(name = "filterType", required = false, defaultValue = "EQ") FilterType filterType,
            @RequestParam(name = "fromDate", required = false) LocalDate fromDate,
            @RequestParam(name = "toDate", required = false) LocalDate toDate
    ){
        return this.logisticCompanyService.getLogisticCompany(companyId, filterType, fromDate, toDate);
    };

    @PostMapping
    public LogisticCompanyDtoResponse createNewLogisticCompany(@RequestBody @Valid LogisticCompanyDto logisticCompanyDto){
        return this.logisticCompanyService.createNewLogisticCompany(logisticCompanyDto);
    };

    @PutMapping("/{companyId}")
    public LogisticCompanyDtoResponse updateLogisticCompany(
            @PathVariable int companyId,
            @RequestBody @Valid LogisticCompanyDto logisticCompanyDto
    ){
        return this.logisticCompanyService.updateLogisticCompany(companyId, logisticCompanyDto);
    };

    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> deleteLogisticCompany(@PathVariable int companyId){
        this.logisticCompanyService.deleteLogisticCompany(companyId);

        return ResponseEntity.ok("Company with id: '" + companyId + "' was successfully deleted!");
    };
}
