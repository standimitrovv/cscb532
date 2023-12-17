package com.example.logisticcompany.service.impl;

import com.example.logisticcompany.models.entity.Client;
import com.example.logisticcompany.models.entity.Shipment;
import com.example.logisticcompany.repository.ClientRepository;
import com.example.logisticcompany.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //TODO
    @Override
    public boolean registerClient(Client client) {
        return false;
    }

    @Override
    public String showName(long id) {
        return null;
    }

    @Override
    public String showAddress(long id) {
        return null;
    }

    @Override
    public String showEmail(long id) {
        return null;
    }

    @Override
    public String showPhoneNumber(long id) {
        return null;
    }

    @Override
    public Set<Shipment> showReceivedPackages(long id) {
        return null;
    }

    @Override
    public Set<Shipment> showSentPackages(long id) {
        return null;
    }

    @Override
    public Set<Shipment> showToBeReceivedPackages(long id) {
        return null;
    }

    @Override
    public boolean changeName(long id, String newName) {
        return false;
    }

    @Override
    public boolean changeAddress(long id, String newAddress) {
        return false;
    }

    @Override
    public boolean changeEmail(long id, String newEmail) {
        return false;
    }

    @Override
    public boolean changePhoneNumber(long id, String newPhoneNumber) {
        return false;
    }

    @Override
    public boolean addNewReceivedPackage(long id, Shipment shipment) {
        return false;
    }

    @Override
    public boolean addNewSentPackage(long id, Shipment shipment) {
        return false;
    }

    @Override
    public boolean addNewToBeReceivedPackage(long id, Shipment shipment) {
        return false;
    }

    @Override
    public boolean deleteClientInfo(long id) {
        return false;
    }
}
