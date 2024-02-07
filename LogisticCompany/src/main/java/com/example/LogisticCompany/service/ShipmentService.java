package com.example.LogisticCompany.service;

import com.example.LogisticCompany.dto.shipment.ShipmentDto;
import com.example.LogisticCompany.dto.shipment.ShipmentDtoResponse;
import com.example.LogisticCompany.dto.shipment.UpdateShipmentStatusDto;
import com.example.LogisticCompany.model.shipment.ShipmentStatus;

import java.util.List;

public interface ShipmentService {

    List<ShipmentDtoResponse> getAllShipments(int employeeId, ShipmentStatus shipmentStatus, int clientId);

    ShipmentDtoResponse getShipment(int shipmentId);

    ShipmentDtoResponse createNewShipment(ShipmentDto shipmentDto);

    ShipmentDtoResponse updateShipmentStatus(int shipmentId, UpdateShipmentStatusDto shipmentDto);

    void deleteShipment(int shipmentId);
}
