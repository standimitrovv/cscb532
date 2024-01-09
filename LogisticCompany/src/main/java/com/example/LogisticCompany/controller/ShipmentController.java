package com.example.LogisticCompany.controller;

import com.example.LogisticCompany.dto.office.OfficeDto;
import com.example.LogisticCompany.dto.office.OfficeDtoResponse;
import com.example.LogisticCompany.dto.shipment.ShipmentDto;
import com.example.LogisticCompany.dto.shipment.ShipmentDtoResponse;
import com.example.LogisticCompany.model.shipment.ShipmentStatus;
import com.example.LogisticCompany.service.implementation.OfficeServiceImpl;
import com.example.LogisticCompany.service.implementation.ShipmentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    private final ShipmentServiceImpl shipmentService;

    @Autowired
    public ShipmentController(ShipmentServiceImpl shipmentService){ this.shipmentService = shipmentService; }

    @GetMapping
    public List<ShipmentDtoResponse> getAllShipments(
            @RequestParam(name = "employeeId", required = false) int employeeId,
            @RequestParam(name = "shipmentStatus", required = false) ShipmentStatus shipmentStatus,
            @RequestParam(name = "clientId", required = false) int clientId
    ){
        return this.shipmentService.getAllShipments(employeeId, shipmentStatus, clientId);
    }

    @GetMapping("/{shipmentId}")
    public ShipmentDtoResponse getShipment(@PathVariable int shipmentId){
        return this.shipmentService.getShipment(shipmentId);
    }

    @PostMapping
    public ShipmentDtoResponse createNewShipment(@RequestBody @Valid ShipmentDto shipmentDto){
        return this.shipmentService.createNewShipment(shipmentDto);
    }

    @PatchMapping("/{shipmentId}")
    public ShipmentDtoResponse updateShipment(
            @PathVariable int shipmentId,
            @RequestBody @Valid ShipmentDto shipmentDto
    ){
        return this.shipmentService.updateShipment(shipmentId, shipmentDto);
    }

    @DeleteMapping("/{shipmentId}")
    public ResponseEntity<String> deleteShipment(@PathVariable int shipmentId){
        this.shipmentService.deleteShipment(shipmentId);

        return ResponseEntity.ok("Shipment with id: '" + shipmentId + "' was successfully deleted!");
    }
}
