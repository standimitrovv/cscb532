package com.example.logisticcompany.repository;

import com.example.logisticcompany.models.entity.LogisticCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogisticCompanyRepository extends JpaRepository<LogisticCompany, Long> {

    @Override
    Optional<LogisticCompany> findById(Long id);
}
