package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.client.ClientDto;
import com.example.LogisticCompany.dto.client.ClientDtoResponse;
import com.example.LogisticCompany.repository.ClientRepository;
import com.example.LogisticCompany.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public ClientDtoResponse getAllClients() {
        return null;
    }

    public ClientDtoResponse getClient(int clientId) {
        return null;
    }

    public ClientDtoResponse createNewClient(ClientDto clientDto) {
        return null;
    }

    public ClientDtoResponse updateClient(int clientId, ClientDto clientDto) {
        return null;
    }

    public void deleteClient(int clientId) {

    }
}
