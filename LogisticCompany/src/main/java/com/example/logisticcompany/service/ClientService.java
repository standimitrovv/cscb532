package com.example.logisticcompany.service;

import com.example.logisticcompany.exceptions.InvalidEmailCharacters;
import com.example.logisticcompany.exceptions.InvalidPhoneNumberCharacters;
import com.example.logisticcompany.exceptions.NonExistingClient;
import com.example.logisticcompany.models.entity.Client;
import com.example.logisticcompany.models.entity.Shipment;

import java.io.IOException;
import java.util.Set;

public interface ClientService {
    boolean registerClient(Client client);
    String showName(long id) throws NonExistingClient;
    String showAddress(long id) throws NonExistingClient;
    String showEmail(long id) throws NonExistingClient;
    String showPhoneNumber(long id) throws NonExistingClient;
    Set<Shipment> showReceivedPackages(long id) throws NonExistingClient;
    Set<Shipment> showSentPackages(long id) throws NonExistingClient;
    Set<Shipment> showToBeReceivedPackages(long id) throws NonExistingClient;
    boolean changeFirstName(long id) throws IOException, InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient;

    boolean changeLastName(long id) throws IOException, InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient;
    boolean changeAddress(long id) throws IOException, InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient;
    boolean changeEmail(long id) throws IOException, InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient;
    boolean changePhoneNumber(long id) throws IOException, InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient;
    boolean addNewReceivedPackage(long clientId, long packageId) throws NonExistingClient, InvalidPhoneNumberCharacters, InvalidEmailCharacters;
    boolean addNewSentPackage(long id);
    boolean addNewToBeReceivedPackage(long id);
    boolean deleteClientInfo(long id);
}
