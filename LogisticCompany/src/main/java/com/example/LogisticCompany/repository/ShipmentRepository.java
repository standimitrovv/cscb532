package com.example.LogisticCompany.repository;

import com.example.LogisticCompany.model.shipment.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {
}
