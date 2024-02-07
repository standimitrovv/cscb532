package com.example.LogisticCompany.service;

import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDto;
import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDtoResponse;
import com.example.LogisticCompany.model.logisticCompany.FilterType;

import java.time.LocalDate;
import java.util.List;

public interface LogisticCompanyService {

    List<LogisticCompanyDtoResponse> getAllLogisticCompanies();

    LogisticCompanyDtoResponse getLogisticCompany(int companyId, FilterType filterType, LocalDate fromDate, LocalDate toDate);

    LogisticCompanyDtoResponse createNewLogisticCompany(LogisticCompanyDto logisticCompanyDto);

    LogisticCompanyDtoResponse updateLogisticCompany(int companyId, LogisticCompanyDto logisticCompanyDto);
}
