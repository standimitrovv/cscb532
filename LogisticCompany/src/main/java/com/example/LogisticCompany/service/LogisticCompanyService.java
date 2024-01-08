package com.example.LogisticCompany.service;

import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDto;
import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDtoResponse;

public interface LogisticCompanyService {

    LogisticCompanyDtoResponse getAllLogisticCompanies();

    LogisticCompanyDtoResponse getLogisticCompany(int companyId);

    LogisticCompanyDtoResponse createNewLogisticCompany(LogisticCompanyDto logisticCompanyDto);

    LogisticCompanyDtoResponse updateLogisticCompany(int companyId, LogisticCompanyDto logisticCompanyDto);

    void deleteLogisticCompany(int companyId);
}
