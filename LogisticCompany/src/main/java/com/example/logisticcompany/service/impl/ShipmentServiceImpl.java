package com.example.logisticcompany.service.impl;

import com.example.logisticcompany.models.entity.Client;
import com.example.logisticcompany.models.entity.Employee;
import com.example.logisticcompany.models.entity.Office;
import com.example.logisticcompany.models.entity.Shipment;
import com.example.logisticcompany.repository.ShipmentRepository;
import com.example.logisticcompany.service.ShipmentService;
import com.example.logisticcompany.util.enums.DeliveryType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;

    public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }


    //TODO
    @Override
    public boolean registerShipment(Shipment shipment) {
        return false;
    }

    @Override
    public Client showSender(long id) {
        return null;
    }

    @Override
    public Client showReceiver(long id) {
        return null;
    }

    @Override
    public String showDeliveryAddress(long id) {
        return null;
    }

    @Override
    public BigDecimal showWeight(long id) {
        return null;
    }

    @Override
    public boolean checkIfSent(long id) {
        return false;
    }

    @Override
    public boolean checkIfDelivered(long id) {
        return false;
    }

    @Override
    public Office showOffice(long id) {
        return null;
    }

    @Override
    public LocalDate showDateOfCreation(long id) {
        return null;
    }

    @Override
    public BigDecimal showDeliveryFee(long id) {
        return null;
    }

    @Override
    public BigDecimal showPrice(long id) {
        return null;
    }

    @Override
    public BigDecimal showPriceWithDeliveryFee(long id) {
        return null;
    }

    @Override
    public DeliveryType showDeliveryType(long id) {
        return null;
    }

    @Override
    public Employee showEmployee(long id) {
        return null;
    }

    @Override
    public boolean changeDeliveryType(long id, DeliveryType deliveryType) {
        return false;
    }

    @Override
    public boolean changeEmployee(long id, Employee newEmployee) {
        return false;
    }

    @Override
    public boolean changeDeliveryAddress(long id, String newAddress) {
        return false;
    }

    @Override
    public boolean changeWeight(long id, BigDecimal newWeight) {
        return false;
    }

    @Override
    public boolean changeOffice(long id, Office newOffice) {
        return false;
    }

    @Override
    public boolean changeDeliveryFee(long id, BigDecimal newDeliveryFee) {
        return false;
    }

    @Override
    public boolean changePrice(long id, BigDecimal newPrice) {
        return false;
    }

    @Override
    public boolean changePriceWithDeliveryFee(long id, BigDecimal newPriceWithDeliveryFee) {
        return false;
    }

    @Override
    public boolean deleteShipment(long id) {
        return false;
    }
}
