package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.shipment.ShipmentDto;
import com.example.LogisticCompany.dto.shipment.ShipmentDtoResponse;
import com.example.LogisticCompany.repository.ShipmentRepository;
import com.example.LogisticCompany.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;

    @Autowired
    public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public List<ShipmentDtoResponse> getAllShipments() {
        return null;
    }

    public ShipmentDtoResponse getShipment(int shipmentId) {
        return null;
    }

    public ShipmentDtoResponse createNewShipment(ShipmentDto shipmentDto) {
        return null;
    }

    public ShipmentDtoResponse updateShipment(int shipmentId, ShipmentDto shipmentDto) {
        return null;
    }

    public void deleteShipment(int shipmentId) {

    }
}
