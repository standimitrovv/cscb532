package com.example.logisticcompany.web.controllers;

import com.example.logisticcompany.models.dto.EmployeeDto;
import com.example.logisticcompany.models.entity.Employee;
import com.example.logisticcompany.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employeess")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // show all of the employee info
    // edit employee info

    @GetMapping("/employee_info")
    public String employeeInfo(EmployeeDto employeeDto) {


        return "employee_info";
    }

    @GetMapping("/employee_info/form")
    public String editUserInfoForm() {


        return "employee_info";
    }

    @PostMapping("/employee_info")
    public String editUserInfo(EmployeeDto employeeDto) {


        return "employee_info";
    }

    @GetMapping("/list")
    public String employeeList(Model model) {
        List<Employee> employees = employeeService.getAllEmployeesInOffice();
        model.addAttribute("employees", employees);
        return "employee-list";
    }
}
