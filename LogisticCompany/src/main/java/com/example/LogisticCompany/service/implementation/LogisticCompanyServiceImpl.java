package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDto;
import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDtoResponse;
import com.example.LogisticCompany.model.logisticCompany.LogisticCompany;
import com.example.LogisticCompany.repository.LogisticCompanyRepository;
import com.example.LogisticCompany.service.LogisticCompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogisticCompanyServiceImpl implements LogisticCompanyService {
    private final LogisticCompanyRepository logisticCompanyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public LogisticCompanyServiceImpl(LogisticCompanyRepository logisticCompanyRepository){
        this.logisticCompanyRepository = logisticCompanyRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<LogisticCompanyDtoResponse> getAllLogisticCompanies() {
        List<LogisticCompany> companies = this.logisticCompanyRepository.findAll();

        return companies
                .stream()
                .map(c -> modelMapper.map(c, LogisticCompanyDtoResponse.class))
                .collect(Collectors.toList());
    }

    public LogisticCompanyDtoResponse getLogisticCompany(int companyId) {
        LogisticCompany company = this.logisticCompanyRepository.findById(companyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return modelMapper.map(company, LogisticCompanyDtoResponse.class);
    }

    public LogisticCompanyDtoResponse createNewLogisticCompany(LogisticCompanyDto logisticCompanyDto) {
        LogisticCompany logisticCompany = new LogisticCompany();

        logisticCompany.setAddress(logisticCompanyDto.getAddress());
        logisticCompany.setEmail(logisticCompanyDto.getEmail());
        logisticCompany.setName(logisticCompanyDto.getName());
        logisticCompany.setPhoneNumber(logisticCompanyDto.getPhoneNumber());

        this.logisticCompanyRepository.save(logisticCompany);

        return modelMapper.map(logisticCompany, LogisticCompanyDtoResponse.class);
    }

    public LogisticCompanyDtoResponse updateLogisticCompany(int companyId, LogisticCompanyDto logisticCompanyDto) {
        LogisticCompany company = this.logisticCompanyRepository.findById(companyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        company.setName(logisticCompanyDto.getName());
        company.setAddress(logisticCompanyDto.getAddress());
        company.setEmail(logisticCompanyDto.getEmail());
        company.setPhoneNumber(company.getPhoneNumber());

        LogisticCompany updatedCompany = logisticCompanyRepository.save(company);

        return modelMapper.map(updatedCompany, LogisticCompanyDtoResponse.class);
    }
}
