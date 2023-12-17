package com.example.logisticcompany.repository;

import com.example.logisticcompany.models.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    @Override
    Optional<Shipment> findById(Long id);
}
