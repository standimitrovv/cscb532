package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDto;
import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDtoResponse;
import com.example.LogisticCompany.model.logisticCompany.FilterType;
import com.example.LogisticCompany.model.logisticCompany.LogisticCompany;
import com.example.LogisticCompany.repository.LogisticCompanyRepository;
import com.example.LogisticCompany.service.LogisticCompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
        return this.logisticCompanyRepository.findAllLogisticCompanies();
    }

    public LogisticCompanyDtoResponse getLogisticCompany(
            int companyId,
            FilterType filterType,
            LocalDate fromDate,
            LocalDate toDate
    ) {
        return this.logisticCompanyRepository.findLogisticCompanyById(companyId).get();
    }

    public LogisticCompanyDtoResponse createNewLogisticCompany(LogisticCompanyDto logisticCompanyDto) {
        LogisticCompany logisticCompany = modelMapper.map(logisticCompanyDto, LogisticCompany.class);
        this.logisticCompanyRepository.saveAndFlush(logisticCompany);

        return modelMapper.map(logisticCompanyDto, LogisticCompanyDtoResponse.class);
    }

    public LogisticCompanyDtoResponse updateLogisticCompany(int companyId, LogisticCompanyDto logisticCompanyDto) {
        LogisticCompany logisticCompany = this.logisticCompanyRepository.findById(companyId).get();

        logisticCompany.setName(logisticCompanyDto.getName());
        logisticCompany.setAddress(logisticCompanyDto.getAddress());
        logisticCompany.setEmail(logisticCompanyDto.getEmail());
        logisticCompany.setPhoneNumber(logisticCompany.getPhoneNumber());

        LogisticCompany updatedCompany = logisticCompanyRepository.save(logisticCompany);

        return modelMapper.map(updatedCompany, LogisticCompanyDtoResponse.class);
    }

    public void deleteLogisticCompany(int companyId) {
        if (logisticCompanyRepository.findById(companyId).isPresent()) {
            LogisticCompany logisticCompany = logisticCompanyRepository.findById(companyId).get();
            this.logisticCompanyRepository.delete(logisticCompany);
        }
    }
}
