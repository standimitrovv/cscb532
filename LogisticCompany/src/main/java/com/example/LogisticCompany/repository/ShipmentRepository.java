package com.example.LogisticCompany.repository;

import com.example.LogisticCompany.dto.shipment.ShipmentDtoResponse;
import com.example.LogisticCompany.model.shipment.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {

    List<ShipmentDtoResponse> findAllShipments();
    Optional<ShipmentDtoResponse> findShipmentById(int id);
}
