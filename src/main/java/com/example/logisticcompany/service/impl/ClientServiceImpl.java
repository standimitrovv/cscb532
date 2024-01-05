package com.example.logisticcompany.service.impl;

import com.example.logisticcompany.exceptions.*;
import com.example.logisticcompany.models.dto.ClientDto;
import com.example.logisticcompany.models.entity.Client;
import com.example.logisticcompany.repository.ClientRepository;
import com.example.logisticcompany.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ResponseEntity<String> registerClient(ClientDto clientDto) throws InvalidRegistration {

        //TODO:
        if (clientRepository.findClientByEmail(clientDto.getEmail()).isPresent()) {
            throw new InvalidRegistration("Client with this email already exists!");
        }

        ModelMapper modelMapper = new ModelMapper();
        Client client = modelMapper.map(clientDto, Client.class);

        String hashedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(hashedPassword);

        clientRepository.saveAndFlush(client);

        return ResponseEntity.ok("Successfully registered client!");
    }

    @Override
    public String showClientInfo(long id) throws NonExistingClient {
        if (searchClientInRepository(id) != null) {
            Client client = searchClientInRepository(id);
            StringBuilder info = new StringBuilder();

            info.append("Full name: ").append(client.getFirstName()).append(client.getLastName());
            info.append(System.lineSeparator());
            info.append("Email: ").append(client.getEmail());
            info.append(System.lineSeparator());
            info.append("Phone number: ").append(client.getPhoneNumber());
            info.append(System.lineSeparator());

            return info.toString();
        }

        throw new NonExistingClient("Unable to find a client's information with this ID.");
    }

    @Override
    public ResponseEntity<String> changeFirstName(Client client, String name) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient, InvalidPasswordCharacters {
        if (searchClientInRepository(client.getId()) == null) {
            throw new NonExistingClient("Unable to find a client with this ID to change his/her first name.");
        }

        // create a client dto and validate the name
        ClientDto clientDto = new ClientDto(name, client.getLastName(), client.getEmail(), client.getPassword(), client.getPhoneNumber());

        // if the name is valid, the current entity client will have a new changed name
        client.setFirstName(clientDto.getFirstName());

        // flush to DB
        clientRepository.saveAndFlush(client);

        return ResponseEntity.ok("Successfully changed first name");
    }

    @Override
    public ResponseEntity<String> changeLastName(Client client, String lastName) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient, InvalidPasswordCharacters {
        if (searchClientInRepository(client.getId()) == null) {
            throw new NonExistingClient("Unable to find a client with this ID to change his/her last name.");
        }

        // create a client dto and validate the name
        ClientDto clientDto = new ClientDto(client.getFirstName(), lastName, client.getPassword(), client.getEmail(), client.getPhoneNumber());

        // if the name is valid, the current entity client will have a new changed name
        client.setLastName(clientDto.getLastName());

        // flush to DB
        clientRepository.saveAndFlush(client);

        return ResponseEntity.ok("Successfully changed last name");
    }

    @Override
    public ResponseEntity<String> changePassword(Client client, String password) throws NonExistingClient, InvalidPhoneNumberCharacters, InvalidEmailCharacters, InvalidPasswordCharacters {
        if (searchClientInRepository(client.getId()) == null) {
            throw new NonExistingClient("Unable to find a client with this ID to change his/her password.");
        }

        // create a client dto and validate the password
        ClientDto clientDto = new ClientDto(client.getFirstName(), client.getLastName(), password, client.getEmail(), client.getPhoneNumber());

        // if the password is valid, the current entity client will have a new changed password
        client.setPassword(clientDto.getPassword());

        // flush to DB
        clientRepository.saveAndFlush(client);

        return ResponseEntity.ok("Successfully changed password");
    }

    @Override
    public ResponseEntity<String> changeEmail(Client client, String email) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient, InvalidPasswordCharacters {
        if (searchClientInRepository(client.getId()) == null) {
            throw new NonExistingClient("Unable to find a client with this ID to change his/her email.");
        }

        // create a client dto and validate the email
        ClientDto clientDto = new ClientDto(client.getFirstName(), client.getLastName(), client.getPassword(), email, client.getPhoneNumber());

        // if the email is valid, the current entity client will have a new changed email
        client.setEmail(clientDto.getEmail());

        // flush to DB
        clientRepository.saveAndFlush(client);

        return ResponseEntity.ok("Successfully changed email");
    }

    @Override
    public ResponseEntity<String> changePhoneNumber(Client client, String phoneNumber) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient, InvalidPasswordCharacters {
        if (searchClientInRepository(client.getId()) == null) {
            throw new NonExistingClient("Unable to find a client with this ID to change his/her phone number.");
        }

        // create a client dto and validate the phone number
        ClientDto clientDto = new ClientDto(client.getFirstName(), client.getLastName(), client.getPassword(), client.getEmail(), phoneNumber);

        // if the phone number is valid, the current entity client will have a new changed phone number
        client.setPhoneNumber(clientDto.getPhoneNumber());

        // flush to DB
        clientRepository.saveAndFlush(client);

        return ResponseEntity.ok("Successfully changed phone number");
    }

    @Override
    public List<Client> listAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public ResponseEntity<String> deleteClientInfo(long id) throws NonExistingClient {
        if (clientRepository.findById(id).isPresent()) {
            Client client = clientRepository.findById(id).get();
            clientRepository.delete(client);

            return ResponseEntity.ok("Successfully deleted client info");
        }

        throw new NonExistingClient("Client is non-existing.");
    }

    private Client searchClientInRepository(long id) {
        if (clientRepository.findById(id).isPresent()) {
            return clientRepository.findById(id).get();
        }
        return null;
    }
}
