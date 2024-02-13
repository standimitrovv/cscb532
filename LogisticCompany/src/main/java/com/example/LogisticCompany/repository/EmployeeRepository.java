package com.example.LogisticCompany.repository;

import com.example.LogisticCompany.dto.client.BaseClientDtoResponse;
import com.example.LogisticCompany.dto.employee.BaseEmployeeDtoResponse;
import com.example.LogisticCompany.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "SELECT e FROM Employee e WHERE e.user.id = :userId")
    Employee findEmployeeByUserId(@Param("userId") int userId);
}
