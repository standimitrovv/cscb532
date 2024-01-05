package com.example.logisticcompany.config;

import com.example.logisticcompany.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ClientService clientService;
    private final EmployeeService employeeService;
    private final LogisticCompanyService logisticCompany;
    private final OfficeService officeService;
    private final ShipmentService shipmentService;
    private final UserService userService;

    public ConsoleRunner(ClientService clientService, EmployeeService employeeService, LogisticCompanyService logisticCompany, OfficeService officeService, ShipmentService shipmentService, UserService userService) {
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.logisticCompany = logisticCompany;
        this.officeService = officeService;
        this.shipmentService = shipmentService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        //TODO
    }
}
