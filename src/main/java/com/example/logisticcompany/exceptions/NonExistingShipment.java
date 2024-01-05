package com.example.logisticcompany.exceptions;

public class NonExistingShipment extends Exception {
    public NonExistingShipment(String message) {
        super(message);
    }
}
