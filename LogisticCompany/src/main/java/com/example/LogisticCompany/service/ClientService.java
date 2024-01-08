package com.example.LogisticCompany.service;

import com.example.LogisticCompany.dto.client.ClientDto;
import com.example.LogisticCompany.dto.client.ClientDtoResponse;

public interface ClientService {

    ClientDtoResponse getAllClients();

    ClientDtoResponse getClient(int clientId);

    ClientDtoResponse createNewClient(ClientDto clientDto);

    ClientDtoResponse updateClient(int clientId, ClientDto clientDto);

    void deleteClient(int clientId);
}
