package com.example.LogisticCompany.controller;

import com.example.LogisticCompany.dto.shipment.ShipmentDto;
import com.example.LogisticCompany.dto.shipment.ShipmentDtoResponse;
import com.example.LogisticCompany.dto.shipment.UpdateShipmentStatusDto;
import com.example.LogisticCompany.model.shipment.ShipmentStatus;
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

    /*
    * Gets all the shipments and allows the user to filter them optionally
    *
    * @param employeeId - to filter all the shipments registered by a certain employee
    * @param shipmentStatus - to filter the shipments by their status (e.g sent but not yet completed)
    * @param clientId - to filter the shipments by which client they were sent/received
    * */
    @GetMapping
    public List<ShipmentDtoResponse> getAllShipments(
            @RequestParam(name = "employeeId", required = false, defaultValue = "0") int employeeId,
            @RequestParam(name = "shipmentStatus", required = false) ShipmentStatus shipmentStatus,
            @RequestParam(name = "clientId", required = false, defaultValue = "0") int clientId
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
    public ShipmentDtoResponse updateShipmentStatus(
            @PathVariable int shipmentId,
            @RequestBody @Valid UpdateShipmentStatusDto shipmentDto
    ){
        return this.shipmentService.updateShipmentStatus(shipmentId, shipmentDto);
    }

    @DeleteMapping("/{shipmentId}")
    public ResponseEntity<String> deleteShipment(@PathVariable int shipmentId){
        this.shipmentService.deleteShipment(shipmentId);

        return ResponseEntity.ok("Shipment with id: '" + shipmentId + "' was successfully deleted!");
    }
}
