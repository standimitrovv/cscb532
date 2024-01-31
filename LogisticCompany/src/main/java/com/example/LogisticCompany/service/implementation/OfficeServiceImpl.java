package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.office.OfficeDto;
import com.example.LogisticCompany.dto.office.OfficeDtoResponse;
import com.example.LogisticCompany.model.Office;
import com.example.LogisticCompany.repository.OfficeRepository;
import com.example.LogisticCompany.service.OfficeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OfficeServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<OfficeDtoResponse> getAllOffices() {
        return this.officeRepository.findAllOffices();
    }

    public OfficeDtoResponse getOffice(int officeId) {
        return this.officeRepository.findOfficeById(officeId).get();
    }

    public OfficeDtoResponse createNewOffice(OfficeDto officeDto) {
        Office office = modelMapper.map(officeDto, Office.class);
        this.officeRepository.saveAndFlush(office);

        return modelMapper.map(office, OfficeDtoResponse.class);
    }

    public OfficeDtoResponse updateOffice(int officeId, OfficeDto officeDto) {
        Office office = this.officeRepository.findById(officeId).get();

        office.setAddress(officeDto.getAddress());
        office.setPhoneNumber(officeDto.getPhoneNumber());

        Office updatedOffice = officeRepository.save(office);

        return modelMapper.map(updatedOffice, OfficeDtoResponse.class);
    }

    public void deleteOffice(int officeId) {
        if (officeRepository.findById(officeId).isPresent()) {
            Office office = officeRepository.findById(officeId).get();
            this.officeRepository.delete(office);
        }
    }
}
