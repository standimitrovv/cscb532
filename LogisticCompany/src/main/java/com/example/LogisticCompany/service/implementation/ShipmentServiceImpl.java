package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.shipment.ShipmentDto;
import com.example.LogisticCompany.dto.shipment.ShipmentDtoResponse;
import com.example.LogisticCompany.model.shipment.Shipment;
import com.example.LogisticCompany.model.shipment.ShipmentStatus;
import com.example.LogisticCompany.repository.ShipmentRepository;
import com.example.LogisticCompany.service.ShipmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<ShipmentDtoResponse> getAllShipments(int employeeId, ShipmentStatus shipmentStatus, int clientId) {
        return this.shipmentRepository.findAllShipments();
    }

    public ShipmentDtoResponse getShipment(int shipmentId) {
        return this.shipmentRepository.findShipmentById(shipmentId).get();
    }

    public ShipmentDtoResponse createNewShipment(ShipmentDto shipmentDto) {
        Shipment shipment = modelMapper.map(shipmentDto, Shipment.class);
        this.shipmentRepository.saveAndFlush(shipment);

        return modelMapper.map(shipment, ShipmentDtoResponse.class);
    }

    public ShipmentDtoResponse updateShipment(int shipmentId, ShipmentDto shipmentDto) {
        Shipment shipment = this.shipmentRepository.findById(shipmentId).get();

        shipment.setWeight(shipmentDto.getWeight());
        shipment.setShipmentCost(shipmentDto.getShipmentCost());
        shipment.setShipmentStatus(shipmentDto.getShipmentStatus());
        shipment.setCreatedAt(shipmentDto.getCreatedAt());
        shipment.setDeliveryAddress(shipmentDto.getDeliveryAddress());
        shipment.setDeliveryFee(shipmentDto.getDeliveryFee());
        shipment.setDeliveryType(shipmentDto.getDeliveryType());

        Shipment updatedShipment = shipmentRepository.save(shipment);

        return modelMapper.map(updatedShipment, ShipmentDtoResponse.class);
    }

    public void deleteShipment(int shipmentId) {
        if (shipmentRepository.findById(shipmentId).isPresent()) {
            Shipment shipment = this.shipmentRepository.findById(shipmentId).get();
            this.shipmentRepository.delete(shipment);
        }
    }
}
