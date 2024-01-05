package com.example.logisticcompany.service.impl;

import com.example.logisticcompany.exceptions.*;
import com.example.logisticcompany.exceptions.NonExistingEmployee;
import com.example.logisticcompany.models.dto.EmployeeDto;
import com.example.logisticcompany.models.entity.Employee;
import com.example.logisticcompany.models.entity.Office;
import com.example.logisticcompany.models.entity.Shipment;
import com.example.logisticcompany.repository.EmployeeRepository;
import com.example.logisticcompany.service.EmployeeService;
import com.example.logisticcompany.util.enums.EmployeeType;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public JsonObject parseJsonObjectFromFile() throws IOException {
        // TODO: file name
        return JsonParser.parseReader(new FileReader("")).getAsJsonObject();
    }

    @Override
    public boolean registerEmployee(Employee employee) {
        return false;
    }

    @Override
    public String showName(long id) throws NonExistingEmployee {
        if (searchEmployeeInRepository(id) != null) {
            Employee employee = searchEmployeeInRepository(id);
            return employee.getFirstName() + " " + employee.getLastName();
        }

        throw new NonExistingEmployee("Unable to find an employee's name with this ID.");
    }

    @Override
    public String showEmail(long id) throws NonExistingEmployee {
        if (searchEmployeeInRepository(id) != null) {
            Employee employee = searchEmployeeInRepository(id);
            return employee.getEmail();
        }

        throw new NonExistingEmployee("Unable to find an employee's email with this ID.");
    }

    @Override
    public String showPhoneNumber(long id) throws NonExistingEmployee {
        if (searchEmployeeInRepository(id) != null) {
            Employee employee = searchEmployeeInRepository(id);
            return employee.getPhoneNumber();
        }

        throw new NonExistingEmployee("Unable to find an employee's phone number with this ID.");
    }

    @Override
    public EmployeeType showRole(long id) throws NonExistingEmployee {
        if (searchEmployeeInRepository(id) != null) {
            Employee employee = searchEmployeeInRepository(id);
            return employee.getRole();
        }

        throw new NonExistingEmployee("Unable to find an employee's role with this ID.");
    }

    @Override
    public Office showOffice(long id) throws NonExistingEmployee {
        if (searchEmployeeInRepository(id) != null) {
            Employee employee = searchEmployeeInRepository(id);
            return employee.getOffice();
        }

        throw new NonExistingEmployee("Unable to find an employee's office with this ID.");
    }

    @Override
    public Set<Shipment> showAllRegisteredShipments(long id) throws NonExistingEmployee {
        if (searchEmployeeInRepository(id) != null) {
            Employee employee = searchEmployeeInRepository(id);
            return employee.getAllRegisteredPackages();
        }

        throw new NonExistingEmployee("Unable to find an employee's registered packages with this ID.");
    }

    @Override
    public boolean changeFirstName(long id) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingEmployee {
        if (searchEmployeeInRepository(id) != null) {
            // get the current employee entity (if existing) by id
            Employee employee = searchEmployeeInRepository(id);
            String jsonName;

            try {
                // read the file and get it (parse it) as a json object
                JsonObject jsonObject = parseJsonObjectFromFile();

                // check if the json object has first_name as a key and get the value
                if (jsonObject.has("first_name")) {
                    jsonName = jsonObject.get("first_name").getAsString();

                    // create an employee dto and validate the name
                    EmployeeDto employeeDto = new EmployeeDto(jsonName, employee.getLastName(), employee.getEmail(), employee.getPhoneNumber(), employee.getRole(), employee.getOffice());

                    // if the name is valid, the current entity employee will have a new changed name
                    employee.setFirstName(employeeDto.getFirstName());

                    // flush to DB
                    employeeRepository.saveAndFlush(employee);

                    // TODO: clear info in the json file

                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new NonExistingEmployee("Unable to find an employee with this ID to change his/her first name.");
    }

    @Override
    public boolean changeLastName(long id) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingEmployee {
        if (searchEmployeeInRepository(id) != null) {
            // get the current employee entity (if existing) by id
            Employee employee = searchEmployeeInRepository(id);
            String jsonName;

            try {
                // read the file and get it (parse it) as a json object
                JsonObject jsonObject = parseJsonObjectFromFile();

                // check if the json object has last_name as a key and get the value
                if (jsonObject.has("last_name")) {
                    jsonName = jsonObject.get("last_name").getAsString();

                    // create an employee dto and validate the name
                    EmployeeDto employeeDto = new EmployeeDto(employee.getFirstName(), jsonName, employee.getEmail(), employee.getPhoneNumber(), employee.getRole(), employee.getOffice());

                    // if the name is valid, the current entity employee will have a new changed name
                    employee.setLastName(employeeDto.getLastName());

                    // flush to DB
                    employeeRepository.saveAndFlush(employee);

                    // TODO: clear info in the json file

                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new NonExistingEmployee("Unable to find an employee with this ID to change his/her last name.");
    }

    @Override
    public boolean changeEmail(long id) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingEmployee {
        if (searchEmployeeInRepository(id) != null) {
            // get the current employee entity (if existing) by id
            Employee employee = searchEmployeeInRepository(id);
            String jsonEmail;

            try {
                // read the file and get it (parse it) as a json object
                JsonObject jsonObject = parseJsonObjectFromFile();

                // check if the json object has email as a key and get the value
                if (jsonObject.has("email")) {
                    jsonEmail = jsonObject.get("email").getAsString();

                    // create an employee dto and validate the email
                    EmployeeDto employeeDto = new EmployeeDto(employee.getFirstName(), employee.getLastName(), jsonEmail, employee.getPhoneNumber(), employee.getRole(), employee.getOffice());

                    // if the email is valid, the current entity employee will have a new changed email
                    employee.setEmail(employeeDto.getEmail());

                    // flush to DB
                    employeeRepository.saveAndFlush(employee);

                    // TODO: clear info in the json file

                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new NonExistingEmployee("Unable to find an employee with this ID to change his/her email.");
    }

    @Override
    public boolean changePhoneNumber(long id) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingEmployee {
        if (searchEmployeeInRepository(id) != null) {
            // get the current employee entity (if existing) by id
            Employee employee = searchEmployeeInRepository(id);
            String jsonPhoneNumber;

            try {
                // read the file and get it (parse it) as a json object
                JsonObject jsonObject = parseJsonObjectFromFile();

                // check if the json object has phone number as a key and get the value
                if (jsonObject.has("phone_number")) {
                    jsonPhoneNumber = jsonObject.get("phone_number").getAsString();

                    // create a an employee and validate the phone number
                    EmployeeDto employeeDto = new EmployeeDto(employee.getFirstName(), employee.getLastName(), employee.getEmail(), jsonPhoneNumber, employee.getRole(), employee.getOffice());

                    // if the phone number is valid, the current entity employee will have a new changed phone number
                    employee.setPhoneNumber(employeeDto.getPhoneNumber());

                    // flush to DB
                    employeeRepository.saveAndFlush(employee);

                    // TODO: clear info in the json file

                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new NonExistingEmployee("Unable to find an employee with this ID to change his/her phone number.");
    }

    // TODO: should there be a changeRoll() method??
    @Override
    public boolean changeRole(long id) {
       return false;
    }

    @Override
    public boolean changeOffice(long id) throws IOException {
        return false;
    }

    @Override
    public boolean addNewRegisteredShipment(long id) {
        return false;
    }

    @Override
    public boolean deleteEmployeeInfo(long id) {
        return false;
    }

    private Employee searchEmployeeInRepository(long id) {
        if (employeeRepository.findById(id).isPresent()) {
            return employeeRepository.findById(id).get();
        }
        return null;
    }
}
