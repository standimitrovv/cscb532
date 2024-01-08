package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.office.OfficeDto;
import com.example.LogisticCompany.dto.office.OfficeDtoResponse;
import com.example.LogisticCompany.repository.OfficeRepository;
import com.example.LogisticCompany.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;

    @Autowired
    public OfficeServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public List<OfficeDtoResponse> getAllOffices() {
        return null;
    }

    public OfficeDtoResponse getOffice(int officeId) {
        return null;
    }

    public OfficeDtoResponse createNewOffice(OfficeDto officeDto) {
        return null;
    }

    public OfficeDtoResponse updateOffice(int officeId, OfficeDto officeDto) {
        return null;
    }

    public void deleteOffice(int officeId) {

    }
}
