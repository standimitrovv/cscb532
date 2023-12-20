package com.example.logisticcompany.service.impl;

import com.example.logisticcompany.exceptions.InvalidEmailCharacters;
import com.example.logisticcompany.exceptions.InvalidPhoneNumberCharacters;
import com.example.logisticcompany.exceptions.NonExistingCompany;
import com.example.logisticcompany.models.dto.LogisticCompanyDto;
import com.example.logisticcompany.models.entity.*;
import com.example.logisticcompany.repository.LogisticCompanyRepository;
import com.example.logisticcompany.service.LogisticCompanyService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Service
public class LogisticCompanyServiceImpl implements LogisticCompanyService {

    private final LogisticCompanyRepository logisticCompanyRepository;

    public LogisticCompanyServiceImpl(LogisticCompanyRepository logisticCompanyRepository) {
        this.logisticCompanyRepository = logisticCompanyRepository;
    }

    public JsonObject parseJsonObjectFromFile() throws IOException {
        // TODO: file name
        return JsonParser.parseReader(new FileReader("")).getAsJsonObject();
    }

    @Override
    public boolean registerCompany(LogisticCompany logisticCompany) {
       return false;
    }

    @Override
    public String showName(long id) throws NonExistingCompany {
        if (searchCompanyInRepository(id) != null) {
            LogisticCompany company = searchCompanyInRepository(id);
            return company.getName();
        }

        throw new NonExistingCompany("Unable to find a company's name with this ID.");
    }

    @Override
    public String showEmail(long id) throws NonExistingCompany {
        if (searchCompanyInRepository(id) != null) {
            LogisticCompany company = searchCompanyInRepository(id);
            return company.getEmail();
        }

        throw new NonExistingCompany("Unable to find a company's email with this ID.");
    }

    @Override
    public String showAddress(long id) throws NonExistingCompany {
        if (searchCompanyInRepository(id) != null) {
            LogisticCompany company = searchCompanyInRepository(id);
            return company.getAddress();
        }

        throw new NonExistingCompany("Unable to find a company's address with this ID.");
    }

    @Override
    public String showPhoneNumber(long id) throws NonExistingCompany {
        if (searchCompanyInRepository(id) != null) {
            LogisticCompany company = searchCompanyInRepository(id);
            return company.getPhoneNumber();
        }

        throw new NonExistingCompany("Unable to find a company's phone number with this ID.");
    }

    @Override
    public Set<Office> showAllOffices(long id) throws NonExistingCompany {
        if (searchCompanyInRepository(id) != null) {
            LogisticCompany company = searchCompanyInRepository(id);
            return company.getOffices();
        }

        throw new NonExistingCompany("Unable to find company's offices with this ID.");
    }

    @Override
    public Set<Employee> showAllEmployees(long id) throws NonExistingCompany {
        if (searchCompanyInRepository(id) != null) {
            LogisticCompany company = searchCompanyInRepository(id);
            return company.getEmployees();
        }

        throw new NonExistingCompany("Unable to find company's employees with this ID.");
    }

    @Override
    public Set<Shipment> showAllRegisteredPackages(long id) throws NonExistingCompany {
        if (searchCompanyInRepository(id) != null) {
            LogisticCompany company = searchCompanyInRepository(id);
            return company.getRegisteredPackages();
        }

        throw new NonExistingCompany("Unable to find company's registered packages with this ID.");
    }

    @Override
    public Set<Client> showAllRegisteredClients(long id) throws NonExistingCompany {
        if (searchCompanyInRepository(id) != null) {
            LogisticCompany company = searchCompanyInRepository(id);
            return company.getRegisteredClients();
        }

        throw new NonExistingCompany("Unable to find company's registered clients with this ID.");
    }

    @Override
    public Map<Employee, Set<Shipment>> showAllRegisteredPackagesByEmployee(long id) throws NonExistingCompany {
        if (searchCompanyInRepository(id) != null) {
            LogisticCompany company = searchCompanyInRepository(id);
            return company.getRegisteredPackagesByEmployee();
        }

        throw new NonExistingCompany("Unable to find company's registered packages by specific employees with this ID.");
    }

    @Override
    public Set<Shipment> showAllSentAndNotReceivedPackages(long id) throws NonExistingCompany {
        if (searchCompanyInRepository(id) != null) {
            LogisticCompany company = searchCompanyInRepository(id);
            return company.getSentAndNotReceivedPackages();
        }

        throw new NonExistingCompany("Unable to find company's sent-and-not-received packages with this ID.");
    }

    @Override
    public boolean changeName(long id) throws NonExistingCompany, InvalidPhoneNumberCharacters, InvalidEmailCharacters {
        if (searchCompanyInRepository(id) != null) {
            // get the current company entity (if existing) by id
            LogisticCompany company = searchCompanyInRepository(id);
            String jsonName;

            try {
                // read the file and get it (parse it) as a json object
                JsonObject jsonObject = parseJsonObjectFromFile();

                // check if the json object has name as a key and get the value
                if (jsonObject.has("name")) {
                    jsonName = jsonObject.get("name").getAsString();

                    // create a company dto and validate the name
                    LogisticCompanyDto logisticCompanyDto = new LogisticCompanyDto(jsonName, company.getAddress(), company.getEmail(), company.getPhoneNumber());

                    // if the name is valid, the current entity company will have a new changed name
                    company.setName(logisticCompanyDto.getName());

                    // flush to DB
                    logisticCompanyRepository.saveAndFlush(company);

                    // TODO: clear info in the json file

                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new NonExistingCompany("Unable to find a company with this ID to change its name.");
    }

    @Override
    public boolean changeEmail(long id) throws NonExistingCompany, InvalidPhoneNumberCharacters, InvalidEmailCharacters {
        if (searchCompanyInRepository(id) != null) {
            // get the current company entity (if existing) by id
            LogisticCompany company = searchCompanyInRepository(id);
            String jsonEmail;

            try {
                // read the file and get it (parse it) as a json object
                JsonObject jsonObject = parseJsonObjectFromFile();

                // check if the json object has email as a key and get the value
                if (jsonObject.has("email")) {
                    jsonEmail = jsonObject.get("email").getAsString();

                    // create a company dto and validate the name
                    LogisticCompanyDto logisticCompanyDto = new LogisticCompanyDto(company.getName(), company.getAddress(), jsonEmail, company.getPhoneNumber());

                    // if the email is valid, the current entity company will have a new changed email
                    company.setEmail(logisticCompanyDto.getEmail());

                    // flush to DB
                    logisticCompanyRepository.saveAndFlush(company);

                    // TODO: clear info in the json file

                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new NonExistingCompany("Unable to find a company with this ID to change its email.");
    }

    @Override
    public boolean changeAddress(long id) throws NonExistingCompany, InvalidPhoneNumberCharacters, InvalidEmailCharacters {
        if (searchCompanyInRepository(id) != null) {
            // get the current company entity (if existing) by id
            LogisticCompany company = searchCompanyInRepository(id);
            String jsonAddress;

            try {
                // read the file and get it (parse it) as a json object
                JsonObject jsonObject = parseJsonObjectFromFile();

                // check if the json object has address as a key and get the value
                if (jsonObject.has("address")) {
                    jsonAddress = jsonObject.get("address").getAsString();

                    // create a company dto and validate the address
                    LogisticCompanyDto logisticCompanyDto = new LogisticCompanyDto(company.getName(), jsonAddress, company.getEmail(), company.getPhoneNumber());

                    // if the address is valid, the current entity company will have a new changed address
                    company.setName(logisticCompanyDto.getName());

                    // flush to DB
                    logisticCompanyRepository.saveAndFlush(company);

                    // TODO: clear info in the json file

                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new NonExistingCompany("Unable to find a company with this ID to change its address.");
    }

    @Override
    public boolean changePhoneNumber(long id) throws NonExistingCompany, InvalidPhoneNumberCharacters, InvalidEmailCharacters {
        if (searchCompanyInRepository(id) != null) {
            // get the current company entity (if existing) by id
            LogisticCompany company = searchCompanyInRepository(id);
            String jsonPhoneNumber;

            try {
                // read the file and get it (parse it) as a json object
                JsonObject jsonObject = parseJsonObjectFromFile();

                // check if the json object has phone number as a key and get the value
                if (jsonObject.has("phone_number")) {
                    jsonPhoneNumber = jsonObject.get("phone_number").getAsString();

                    // create a company dto and validate the phone number
                    LogisticCompanyDto logisticCompanyDto = new LogisticCompanyDto(company.getName(), company.getAddress(), company.getEmail(), jsonPhoneNumber);

                    // if the phone number is valid, the current entity company will have a new changed phone number
                    company.setName(logisticCompanyDto.getName());

                    // flush to DB
                    logisticCompanyRepository.saveAndFlush(company);

                    // TODO: clear info in the json file

                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new NonExistingCompany("Unable to find a company with this ID to change its phone number.");
    }

    @Override
    public boolean addNewOffice(long id) {
        return false;
    }

    @Override
    public boolean removeOffice(long id) {
        return false;
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
    public boolean addNewRegisteredPackage(long id) {
        return false;
    }

    @Override
    public boolean addNewClient(long id) {
        return false;
    }

    @Override
    public boolean removeClient(long id) {
        return false;
    }

    @Override
    public boolean addNewRegisteredPackageByEmployee(long id) {
        return false;
    }

    @Override
    public boolean addNewSentAndNotReceivedPackage(long id) {
        return false;
    }

    @Override
    public boolean deleteCompanyInfo(long id) {
        return false;
    }

    private LogisticCompany searchCompanyInRepository(long id) {
        if (logisticCompanyRepository.findById(id).isPresent()) {
            return logisticCompanyRepository.findById(id).get();
        }
        return null;
    }
}
