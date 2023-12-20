package com.example.logisticcompany.service.impl;

import com.example.logisticcompany.exceptions.InvalidPhoneNumberCharacters;
import com.example.logisticcompany.exceptions.NonExistingOffice;
import com.example.logisticcompany.models.dto.OfficeDto;
import com.example.logisticcompany.models.entity.Employee;
import com.example.logisticcompany.models.entity.Office;
import com.example.logisticcompany.repository.OfficeRepository;
import com.example.logisticcompany.service.OfficeService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;

    public OfficeServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public JsonObject parseJsonObjectFromFile() throws IOException {
        // TODO: file name
        return JsonParser.parseReader(new FileReader("")).getAsJsonObject();
    }

    @Override
    public boolean registerOffice(Office office) {
        return false;
    }

    @Override
    public String showPhoneNumber(long id) throws NonExistingOffice {
        if (searchOfficeInRepository(id) != null) {
            Office office = searchOfficeInRepository(id);
            return office.getPhoneNumber();
        }

        throw new NonExistingOffice("Unable to find an office's number with this ID.");
    }

    @Override
    public String showAddress(long id) throws NonExistingOffice {
        if (searchOfficeInRepository(id) != null) {
            Office office = searchOfficeInRepository(id);
            return office.getAddress();
        }

        throw new NonExistingOffice("Unable to find an office's address with this ID.");
    }

    @Override
    public Set<Employee> showEmployees(long id) throws NonExistingOffice {
        if (searchOfficeInRepository(id) != null) {
            Office office = searchOfficeInRepository(id);
            return office.getEmployees();
        }

        throw new NonExistingOffice("Unable to find an office's employees with this ID.");
    }

    @Override
    public boolean changePhoneNumber(long id) throws NonExistingOffice, InvalidPhoneNumberCharacters {
        if (searchOfficeInRepository(id) != null) {
            // get the current company entity (if existing) by id
            Office office = searchOfficeInRepository(id);
            String jsonPhoneNumber;

            try {
                // read the file and get it (parse it) as a json object
                JsonObject jsonObject = parseJsonObjectFromFile();

                // check if the json object has phone number as a key and get the value
                if (jsonObject.has("phone_number")) {
                    jsonPhoneNumber = jsonObject.get("phone_number").getAsString();

                    // create a office dto and validate the phone number
                    OfficeDto officeDto = new OfficeDto(office.getAddress(), jsonPhoneNumber);

                    // if the phone number is valid, the current entity office will have a new changed phone number
                    office.setPhoneNumber(officeDto.getPhoneNumber());

                    // flush to DB
                    officeRepository.saveAndFlush(office);

                    // TODO: clear info in the json file

                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new NonExistingOffice("Unable to find an office with this ID to change its phone number.");
    }

    @Override
    public boolean changeAddress(long id) throws NonExistingOffice, InvalidPhoneNumberCharacters {
        if (searchOfficeInRepository(id) != null) {
            // get the current company entity (if existing) by id
            Office office = searchOfficeInRepository(id);
            String jsonAddress;

            try {
                // read the file and get it (parse it) as a json object
                JsonObject jsonObject = parseJsonObjectFromFile();

                // check if the json object has address as a key and get the value
                if (jsonObject.has("address")) {
                    jsonAddress = jsonObject.get("address").getAsString();

                    // create a office dto and validate the address
                    OfficeDto officeDto = new OfficeDto(office.getAddress(), jsonAddress);

                    // if the address is valid, the current entity office will have a new changed address
                    office.setPhoneNumber(officeDto.getPhoneNumber());

                    // flush to DB
                    officeRepository.saveAndFlush(office);

                    // TODO: clear info in the json file

                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new NonExistingOffice("Unable to find an office with this ID to change its address.");
    }

    @Override
    public boolean addNewEmployee(long id) {
        return false;
    }

    @Override
    public boolean removeEmployee(long id) {
        return false;
    }

    @Override
    public boolean deleteOfficeInfo(long id) {
        return false;
    }

    private Office searchOfficeInRepository(long id) {
        if (officeRepository.findById(id).isPresent()) {
            return officeRepository.findById(id).get();
        }
        return null;
    }
}
