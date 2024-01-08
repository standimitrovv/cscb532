package com.example.LogisticCompany.controller;

import com.example.LogisticCompany.dto.employee.EmployeeDto;
import com.example.LogisticCompany.dto.employee.EmployeeDtoResponse;
import com.example.LogisticCompany.service.implementation.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService){ this.employeeService = employeeService; }

    @GetMapping
    public EmployeeDtoResponse getAllEmployees(){
         return this.employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public EmployeeDtoResponse getEmployee(@PathVariable int employeeId){
         return this.employeeService.getEmployee(employeeId);
    }

    @PostMapping
    public EmployeeDtoResponse createNewEmployee(@RequestBody @Valid EmployeeDto employeeDto){
         return this.employeeService.createNewEmployee(employeeDto);
    }

    @PatchMapping("/{employeeId}")
    public EmployeeDtoResponse updateEmployee(
            @PathVariable int employeeId,
            @RequestBody @Valid EmployeeDto employeeDto
    ){
        return this.employeeService.updateEmployee(employeeId, employeeDto);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId){
        this.employeeService.deleteEmployee(employeeId);

        return ResponseEntity.ok("Employee with id: '" + employeeId + "' was successfully deleted!");
    }
}
