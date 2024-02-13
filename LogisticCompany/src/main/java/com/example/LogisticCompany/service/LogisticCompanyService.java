package com.example.LogisticCompany.service;

import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDto;
import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDtoResponse;

import java.util.List;

public interface LogisticCompanyService {

    List<LogisticCompanyDtoResponse> getAllLogisticCompanies();

    LogisticCompanyDtoResponse getLogisticCompany(int companyId);

    LogisticCompanyDtoResponse createNewLogisticCompany(LogisticCompanyDto logisticCompanyDto);

    LogisticCompanyDtoResponse updateLogisticCompany(int companyId, LogisticCompanyDto logisticCompanyDto);
}
