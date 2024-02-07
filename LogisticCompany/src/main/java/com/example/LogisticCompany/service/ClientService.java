package com.example.LogisticCompany.service;

import com.example.LogisticCompany.dto.client.ClientDto;
import com.example.LogisticCompany.dto.client.ClientDtoResponse;
import com.example.LogisticCompany.dto.client.UpdateClientDto;

import java.util.List;

public interface ClientService {

    List<ClientDtoResponse> getAllClients();

    ClientDtoResponse getClient(int clientId);

    ClientDtoResponse createNewClient(ClientDto clientDto);

    void setUser(int clientId, int userId);

    ClientDtoResponse updateClient(int clientId, UpdateClientDto clientDto);

    void deleteClient(int clientId);
}
