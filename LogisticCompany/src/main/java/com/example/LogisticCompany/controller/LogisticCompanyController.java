package com.example.LogisticCompany.controller;

import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDto;
import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDtoResponse;
import com.example.LogisticCompany.service.implementation.LogisticCompanyServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logisticCompanies")
public class LogisticCompanyController {

    private final LogisticCompanyServiceImpl logisticCompanyService;

    @Autowired
    public LogisticCompanyController(LogisticCompanyServiceImpl logisticCompanyService) {
        this.logisticCompanyService = logisticCompanyService;
    }

    @GetMapping
    public LogisticCompanyDtoResponse getAllLogisticCompanies(){
        return this.logisticCompanyService.getAllLogisticCompanies();
    };

    @GetMapping("/{companyId}")
    public LogisticCompanyDtoResponse getLogisticCompany(@PathVariable int companyId){
        return this.logisticCompanyService.getLogisticCompany(companyId);
    };

    @PostMapping
    public LogisticCompanyDtoResponse createNewLogisticCompany(@RequestBody @Valid LogisticCompanyDto logisticCompanyDto){
        return this.logisticCompanyService.createNewLogisticCompany(logisticCompanyDto);
    };

    @PatchMapping("/{companyId}")
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
