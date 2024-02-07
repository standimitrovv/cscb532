package com.example.LogisticCompany.service;

import com.example.LogisticCompany.dto.office.BaseOfficeDtoResponse;
import com.example.LogisticCompany.dto.office.OfficeDto;
import com.example.LogisticCompany.dto.office.OfficeDtoResponse;
import com.example.LogisticCompany.dto.office.UpdateOfficeDto;

import java.util.List;

public interface OfficeService {

    List<OfficeDtoResponse> getAllOffices();

    OfficeDtoResponse getOffice(int officeId);

    OfficeDtoResponse createNewOffice(OfficeDto officeDto);

    OfficeDtoResponse updateOffice(int officeId, UpdateOfficeDto officeDto);

    void deleteOffice(int officeId);
}
