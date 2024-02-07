package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.client.ClientDto;
import com.example.LogisticCompany.dto.client.ClientDtoResponse;
import com.example.LogisticCompany.dto.client.UpdateClientDto;
import com.example.LogisticCompany.dto.employee.EmployeeDtoResponse;
import com.example.LogisticCompany.model.Client;
import com.example.LogisticCompany.model.employee.Employee;
import com.example.LogisticCompany.model.user.User;
import com.example.LogisticCompany.model.user.UserType;
import com.example.LogisticCompany.repository.ClientRepository;
import com.example.LogisticCompany.repository.UserRepository;
import com.example.LogisticCompany.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, UserRepository userRepository){
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<ClientDtoResponse> getAllClients() {
        List<Client> clients = this.clientRepository.findAll();

        return clients
                .stream()
                .map(c -> modelMapper.map(c, ClientDtoResponse.class))
                .collect(Collectors.toList());
    }

    public ClientDtoResponse getClient(int clientId) {
        Client c = this.clientRepository.findById(clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return modelMapper.map(c, ClientDtoResponse.class);
    }

    public ClientDtoResponse createNewClient(ClientDto clientDto) {
        Client client = modelMapper.map(clientDto, Client.class);

        this.clientRepository.saveAndFlush(client);

        return modelMapper.map(client, ClientDtoResponse.class);
    }

    public void setUser(int clientId, int userId) {
        Client client = this.clientRepository.findById(clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(user.getEmployee() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        user.setUserType(UserType.CLIENT);

        client.setUser(user);
        this.clientRepository.save(client);
    }

    public ClientDtoResponse updateClient(int clientId, UpdateClientDto clientDto) {
        Client c = this.clientRepository.findById(clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        c.setFullName(clientDto.getFullName());
        c.setEmail(clientDto.getEmail());
        c.setPhoneNumber(clientDto.getPhoneNumber());

        Client updatedClient = clientRepository.save(c);

        return modelMapper.map(updatedClient, ClientDtoResponse.class);
    }

    public void deleteClient(int clientId) {
        Client c = this.clientRepository.findById(clientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        this.clientRepository.delete(c);
    }
}
