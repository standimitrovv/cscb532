package com.example.logisticcompany.service;

import com.example.logisticcompany.models.entity.Client;
import com.example.logisticcompany.models.entity.Shipment;

import java.util.Set;

public interface ClientService {
    boolean registerClient(Client client);
    String showName(long id);
    String showAddress(long id);
    String showEmail(long id);
    String showPhoneNumber(long id);
    Set<Shipment> showReceivedPackages(long id);
    Set<Shipment> showSentPackages(long id);
    Set<Shipment> showToBeReceivedPackages(long id);
    boolean changeName(long id, String newName);
    boolean changeAddress(long id, String newAddress);
    boolean changeEmail(long id, String newEmail);
    boolean changePhoneNumber(long id, String newPhoneNumber);
    boolean addNewReceivedPackage(long id, Shipment shipment);
    boolean addNewSentPackage(long id, Shipment shipment);
    boolean addNewToBeReceivedPackage(long id, Shipment shipment);
    boolean deleteClientInfo(long id);
}
