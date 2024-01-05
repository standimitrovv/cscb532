package com.example.logisticcompany.service;

import com.example.logisticcompany.exceptions.InvalidNegativeOrZeroValue;
import com.example.logisticcompany.exceptions.InvalidRights;
import com.example.logisticcompany.exceptions.InvalidType;
import com.example.logisticcompany.exceptions.NonExistingShipment;
import com.example.logisticcompany.models.entity.*;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface ShipmentService {
    String showShipmentInfo(long id) throws NonExistingShipment;
    boolean changeDeliveryType(long id) throws NonExistingShipment;
    ResponseEntity<String> changeDeliveryAddressForOffice(Shipment shipment, Office office) throws InvalidType, NonExistingShipment;
    ResponseEntity<String> changeDeliveryAddressForOtherAddress(Shipment shipment, String address) throws InvalidType, NonExistingShipment, InvalidNegativeOrZeroValue;
    ResponseEntity<String> changeWeight(Shipment shipment, BigDecimal weight) throws InvalidType, InvalidNegativeOrZeroValue, NonExistingShipment;
    List<Shipment> getAllRegisteredPackages();
    List<Shipment> getAllSentAndNotReceivedPackages();
    List<Shipment> getAllRegisteredPackagesByEmployee(long employeeId);
    BigDecimal calculateShipmentPrice(long id) throws NonExistingShipment;
    List<Shipment> getShipmentsBySender(Client client);
    List<Shipment> getNotDeliveredShipmentsByReceiver(Client client);
    List<Shipment> getDeliveredShipmentsByReceiver(Client client);
    List<Shipment> getShipments(User user) throws InvalidType;
    ResponseEntity<String> deleteShipment(User user, long id) throws NonExistingShipment, InvalidRights;
}
