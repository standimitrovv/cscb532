package com.example.logisticcompany.repository;

import com.example.logisticcompany.models.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Override
    Optional<Employee> findById(Long id);

    Optional<Employee> findEmployeeByEmail(String email);

    List<Employee> findEmployeesByOffice(long officeId);
}
