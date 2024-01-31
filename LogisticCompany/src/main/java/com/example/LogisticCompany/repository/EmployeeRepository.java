package com.example.LogisticCompany.repository;

import com.example.LogisticCompany.dto.employee.EmployeeDtoResponse;
import com.example.LogisticCompany.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<EmployeeDtoResponse> findAllEmployees();

    Optional<EmployeeDtoResponse> findEmployeesById(int id);
}
