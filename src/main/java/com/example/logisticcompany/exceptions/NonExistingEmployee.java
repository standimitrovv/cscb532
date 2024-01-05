package com.example.logisticcompany.exceptions;

public class NonExistingEmployee extends Exception {
    public NonExistingEmployee(String message) {
        super(message);
    }
}
