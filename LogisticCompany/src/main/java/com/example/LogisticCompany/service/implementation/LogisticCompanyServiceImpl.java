package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDto;
import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDtoResponse;
import com.example.LogisticCompany.model.logisticCompany.FilterType;
import com.example.LogisticCompany.repository.LogisticCompanyRepository;
import com.example.LogisticCompany.service.LogisticCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LogisticCompanyServiceImpl implements LogisticCompanyService {
    private final LogisticCompanyRepository logisticCompanyRepository;

    @Autowired
    public LogisticCompanyServiceImpl(LogisticCompanyRepository logisticCompanyRepository){
        this.logisticCompanyRepository = logisticCompanyRepository;
    }

    public List<LogisticCompanyDtoResponse> getAllLogisticCompanies() {
        return null;
    }

    public LogisticCompanyDtoResponse getLogisticCompany(
            int companyId,
            FilterType filterType,
            LocalDate fromDate,
            LocalDate toDate
    ) {
        return null;
    }

    public LogisticCompanyDtoResponse createNewLogisticCompany(LogisticCompanyDto logisticCompanyDto) {
        return null;
    }

    public LogisticCompanyDtoResponse updateLogisticCompany(int companyId, LogisticCompanyDto logisticCompanyDto) {
        return null;
    }

    public void deleteLogisticCompany(int companyId) {

    }
}
