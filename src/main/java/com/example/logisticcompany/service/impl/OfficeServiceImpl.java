package com.example.logisticcompany.service.impl;

import com.example.logisticcompany.exceptions.InvalidPhoneNumberCharacters;
import com.example.logisticcompany.exceptions.InvalidRegistration;
import com.example.logisticcompany.exceptions.NonExistingOffice;
import com.example.logisticcompany.models.dto.OfficeDto;
import com.example.logisticcompany.models.entity.Office;
import com.example.logisticcompany.repository.OfficeRepository;
import com.example.logisticcompany.service.OfficeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;

    public OfficeServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Override
    public ResponseEntity<String> registerOffice(OfficeDto officeDto) throws InvalidRegistration {
        if (officeRepository.findByAddress(officeDto.getAddress()).isPresent()) {
            throw new InvalidRegistration("Office with this address already exists!");
        }

        ModelMapper modelMapper = new ModelMapper();
        Office office = modelMapper.map(officeDto, Office.class);
         officeRepository.saveAndFlush(office);

        return ResponseEntity.ok("Successfully registered office!");
    }

    @Override
    public String showOfficeInfo(long id) throws NonExistingOffice {
        if (searchOfficeInRepository(id) != null) {
            Office office = searchOfficeInRepository(id);
            StringBuilder info = new StringBuilder();

            info.append("Address: ").append(office.getAddress());
            info.append(System.lineSeparator());
            info.append("Phone number: ").append(office.getPhoneNumber());
            info.append(System.lineSeparator());

            return info.toString();
        }

        throw new NonExistingOffice("Unable to find an office's information with this ID.");
    }

    @Override
    public ResponseEntity<String> changePhoneNumber(Office office, String phoneNumber) throws InvalidPhoneNumberCharacters, NonExistingOffice {
        if (searchOfficeInRepository(office.getId()) == null) {
            throw new NonExistingOffice("Unable to find an office with this ID to change his/her phone number.");
        }

        // create an office dto and validate the phone number
        OfficeDto officeDto = new OfficeDto(office.getAddress(), phoneNumber);

        // if the phone number is valid, the current entity office will have a new changed phone number
        office.setPhoneNumber(officeDto.getPhoneNumber());

        // flush to DB
        officeRepository.saveAndFlush(office);

        return ResponseEntity.ok("Successfully changed phone number");
    }

    @Override
    public ResponseEntity<String> changeAddress(Office office, String address) throws NonExistingOffice, InvalidPhoneNumberCharacters {
        if (searchOfficeInRepository(office.getId()) == null) {
            throw new NonExistingOffice("Unable to find an office with this ID to change his/her phone number.");
        }

        // create an office dto and validate the address
        OfficeDto officeDto = new OfficeDto(address, office.getPhoneNumber());

        // if the address is valid, the current entity office will have a new changed address
        office.setAddress(officeDto.getAddress());

        // flush to DB
        officeRepository.saveAndFlush(office);

        return ResponseEntity.ok("Successfully changed address");
    }

    @Override
    public List<Office> showAllOffices() {
        return officeRepository.findAll();
    }

    @Override
    public ResponseEntity<String> deleteOfficeInfo(long id) throws NonExistingOffice {
        if (officeRepository.findById(id).isPresent()) {
            Office office = officeRepository.findById(id).get();
            officeRepository.delete(office);

            return ResponseEntity.ok("Successfully deleted office info");
        }

        throw new NonExistingOffice("Office is non-existing.");
    }

    private Office searchOfficeInRepository(long id) {
        if (officeRepository.findById(id).isPresent()) {
            return officeRepository.findById(id).get();
        }
        return null;
    }
}
