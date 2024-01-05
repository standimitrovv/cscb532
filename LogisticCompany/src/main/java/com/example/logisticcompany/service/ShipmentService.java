package com.example.logisticcompany.service;

import com.example.logisticcompany.exceptions.InvalidNegativeOrZeroValue;
import com.example.logisticcompany.exceptions.InvalidType;
import com.example.logisticcompany.exceptions.NonExistingShipment;
import com.example.logisticcompany.models.entity.Client;
import com.example.logisticcompany.models.entity.Employee;
import com.example.logisticcompany.models.entity.Office;
import com.example.logisticcompany.models.entity.Shipment;
import com.example.logisticcompany.util.enums.DeliveryType;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ShipmentService {


    // TODO:
    //  are these methods needed:
//    boolean changeIfSent(long id);
//    boolean changeIfDelivered(long id);
//    boolean changeDateOfCreation(long id);

    // TODO: should the method calculateShipment() be here or just in the DB

    boolean registerShipment(Shipment shipment);
    Client showSender(long id) throws NonExistingShipment;
    Client showReceiver(long id) throws NonExistingShipment;
    String showDeliveryAddress(long id) throws NonExistingShipment;
    BigDecimal showWeight(long id) throws NonExistingShipment;
    boolean checkIfSent(long id) throws NonExistingShipment;
    boolean checkIfDelivered(long id) throws NonExistingShipment;
    Office showOffice(long id) throws NonExistingShipment;
    LocalDate showDateOfCreation(long id) throws NonExistingShipment;
    BigDecimal showDeliveryFee(long id) throws NonExistingShipment;
    BigDecimal showPrice(long id) throws NonExistingShipment;
    BigDecimal showPriceWithDeliveryFee(long id) throws NonExistingShipment;
    DeliveryType showDeliveryType(long id) throws NonExistingShipment;
    Employee showEmployee(long id) throws NonExistingShipment;
    // void deliverPackage();
    boolean changeDeliveryType(long id);
    boolean changeEmployee(long id);
    boolean changeDeliveryAddress(long id);
    boolean changeWeight(long id) throws InvalidType, InvalidNegativeOrZeroValue, NonExistingShipment;
    boolean changeOffice(long id);
    boolean changeDeliveryFee(long id);
    boolean changePrice(long id);
    boolean changePriceWithDeliveryFee(long id);
    boolean deleteShipment(long id);

}
