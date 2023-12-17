package com.example.logisticcompany.service;

import com.example.logisticcompany.models.entity.Client;
import com.example.logisticcompany.models.entity.Employee;
import com.example.logisticcompany.models.entity.Office;
import com.example.logisticcompany.models.entity.Shipment;
import com.example.logisticcompany.util.DeliveryType;

import java.math.BigDecimal;
import java.util.Date;

public interface ShipmentService {


    // TODO:
    //  are these methods needed:
//    boolean changeIfSent(long id);
//    boolean changeIfDelivered(long id);
//    boolean changeDateOfCreation(long id);

    // TODO: should the method calculateShipment() be here or just in the DB

    boolean registerShipment(Shipment shipment);
    Client showSender(long id);
    Client showReceiver(long id);
    String showDeliveryAddress(long id);
    BigDecimal showWeight(long id);
    boolean checkIfSent(long id);
    boolean checkIfDelivered(long id);
    Office showOffice(long id);
    Date showDateOfCreation(long id);
    BigDecimal showDeliveryFee(long id);
    BigDecimal showPrice(long id);
    BigDecimal showPriceWithDeliveryFee(long id);
    DeliveryType showDeliveryType(long id);
    Employee showEmployee(long id);
    boolean changeDeliveryType(long id, DeliveryType deliveryType);
    boolean changeEmployee(long id, Employee newEmployee);
    boolean changeDeliveryAddress(long id, String newAddress);
    boolean changeWeight(long id, BigDecimal newWeight);
    boolean changeOffice(long id, Office newOffice);
    boolean changeDeliveryFee(long id, BigDecimal newDeliveryFee);
    boolean changePrice(long id, BigDecimal newPrice);
    boolean changePriceWithDeliveryFee(long id, BigDecimal newPriceWithDeliveryFee);
    boolean deleteShipment(long id);

}
