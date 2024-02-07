package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.office.BaseOfficeDtoResponse;
import com.example.LogisticCompany.dto.office.OfficeDto;
import com.example.LogisticCompany.dto.office.OfficeDtoResponse;
import com.example.LogisticCompany.dto.office.UpdateOfficeDto;
import com.example.LogisticCompany.model.Office;
import com.example.LogisticCompany.model.logisticCompany.LogisticCompany;
import com.example.LogisticCompany.repository.LogisticCompanyRepository;
import com.example.LogisticCompany.repository.OfficeRepository;
import com.example.LogisticCompany.service.OfficeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;
    private final LogisticCompanyRepository logisticCompanyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OfficeServiceImpl(OfficeRepository officeRepository, LogisticCompanyRepository logisticCompanyRepository) {
        this.officeRepository = officeRepository;
        this.logisticCompanyRepository = logisticCompanyRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<OfficeDtoResponse> getAllOffices() {
        List<Office> offices = this.officeRepository.findAll();

        return offices
                .stream()
                .map(o -> modelMapper.map(o, OfficeDtoResponse.class))
                .collect(Collectors.toList());
    }

    public OfficeDtoResponse getOffice(int officeId) {
        Office office = this.officeRepository.findById(officeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return modelMapper.map(office, OfficeDtoResponse.class);
    }

    public OfficeDtoResponse createNewOffice(OfficeDto officeDto) {

        Office tempOffice = new Office();
        tempOffice.setAddress(officeDto.getAddress());
        tempOffice.setPhoneNumber(officeDto.getPhoneNumber());

        LogisticCompany company = this.logisticCompanyRepository.findById(officeDto.getCompanyId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        tempOffice.setLogisticCompany(company);

        this.officeRepository.save(tempOffice);

        return modelMapper.map(tempOffice, OfficeDtoResponse.class);
    }

    public OfficeDtoResponse updateOffice(int officeId, UpdateOfficeDto officeDto) {
        Office office = this.officeRepository.findById(officeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        office.setAddress(officeDto.getAddress());
        office.setPhoneNumber(officeDto.getPhoneNumber());

        Office updatedOffice = officeRepository.save(office);

        return modelMapper.map(updatedOffice, OfficeDtoResponse.class);
    }

    public void deleteOffice(int officeId) {
        Office office = this.officeRepository.findById(officeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        this.officeRepository.delete(office);
    }
}
