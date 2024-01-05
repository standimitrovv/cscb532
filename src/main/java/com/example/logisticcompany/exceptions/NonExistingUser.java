package com.example.logisticcompany.exceptions;

public class NonExistingUser extends Exception {
    public NonExistingUser(String message) {
        super(message);
    }
}
