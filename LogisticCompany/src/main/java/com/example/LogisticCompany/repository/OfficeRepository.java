package com.example.LogisticCompany.repository;

import com.example.LogisticCompany.dto.office.OfficeDtoResponse;
import com.example.LogisticCompany.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Integer> {

    List<OfficeDtoResponse> findAllOffices();

    Optional<OfficeDtoResponse> findOfficeById(int id);
}
