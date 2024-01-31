package com.example.LogisticCompany.repository;

import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDtoResponse;
import com.example.LogisticCompany.model.logisticCompany.LogisticCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LogisticCompanyRepository extends JpaRepository<LogisticCompany, Integer> {

    List<LogisticCompanyDtoResponse> findAllLogisticCompanies();

    Optional<LogisticCompanyDtoResponse> findLogisticCompanyById(int id);
}
