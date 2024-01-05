package com.example.logisticcompany.repository;

import com.example.logisticcompany.models.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {

    @Override
    Optional<Office> findById(Long id);

    Optional<Office> findByAddress(String address);
}
