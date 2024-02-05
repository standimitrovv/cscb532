package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.shipment.ShipmentDto;
import com.example.LogisticCompany.dto.shipment.ShipmentDtoResponse;
import com.example.LogisticCompany.model.shipment.Shipment;
import com.example.LogisticCompany.model.shipment.ShipmentStatus;
import com.example.LogisticCompany.repository.ShipmentRepository;
import com.example.LogisticCompany.service.ShipmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Shipment> shipments = this.shipmentRepository.findAll();

        return shipments
                .stream()
                .map(sh -> modelMapper.map(sh, ShipmentDtoResponse.class))
                .collect(Collectors.toList());
    }

    public ShipmentDtoResponse getShipment(int shipmentId) {
        Shipment shipment = this.shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return modelMapper.map(shipment, ShipmentDtoResponse.class);
    }

    public ShipmentDtoResponse createNewShipment(ShipmentDto shipmentDto) {
        Shipment shipment = modelMapper.map(shipmentDto, Shipment.class);
        this.shipmentRepository.saveAndFlush(shipment);

        return modelMapper.map(shipment, ShipmentDtoResponse.class);
    }

    public ShipmentDtoResponse updateShipment(int shipmentId, ShipmentDto shipmentDto) {
        Shipment shipment = this.shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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
        Shipment shipment = this.shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        this.shipmentRepository.delete(shipment);
    }
}
