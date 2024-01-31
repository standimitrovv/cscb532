package com.example.LogisticCompany.repository;

import com.example.LogisticCompany.dto.income.IncomeDtoResponse;
import com.example.LogisticCompany.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {

    List<IncomeDtoResponse> findAllIncomes();

    Optional<IncomeDtoResponse> findIncomeById(int id);
}
