package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.client.ClientDto;
import com.example.LogisticCompany.dto.client.ClientDtoResponse;
import com.example.LogisticCompany.dto.employee.EmployeeDtoResponse;
import com.example.LogisticCompany.model.Client;
import com.example.LogisticCompany.model.employee.Employee;
import com.example.LogisticCompany.repository.ClientRepository;
import com.example.LogisticCompany.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<ClientDtoResponse> getAllClients() {
        return this.clientRepository.findAllClients();
    }

    public ClientDtoResponse getClient(int clientId) {
        return this.clientRepository.findClientById(clientId).get();
    }

    public ClientDtoResponse createNewClient(ClientDto clientDto) {
        Client client = modelMapper.map(clientDto, Client.class);
        this.clientRepository.saveAndFlush(client);

        return modelMapper.map(client, ClientDtoResponse.class);
    }

    public ClientDtoResponse updateClient(int clientId, ClientDto clientDto) {
        Client client = this.clientRepository.findById(clientId).get();

        client.setFullName(clientDto.getFullName());
        client.setEmail(clientDto.getEmail());
        client.setPhoneNumber(clientDto.getPhoneNumber());

        Client updatedClient = clientRepository.save(client);

        return modelMapper.map(updatedClient, ClientDtoResponse.class);
    }

    public void deleteClient(int clientId) {
        if (clientRepository.findById(clientId).isPresent()) {
            Client client = clientRepository.findById(clientId).get();
            this.clientRepository.delete(client);
        }
    }
}
