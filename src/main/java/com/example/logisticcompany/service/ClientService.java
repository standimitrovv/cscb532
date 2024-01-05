package com.example.logisticcompany.service;

import com.example.logisticcompany.exceptions.*;
import com.example.logisticcompany.models.dto.ClientDto;
import com.example.logisticcompany.models.entity.Client;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface ClientService {
    ResponseEntity<String> registerClient(ClientDto clientDto) throws InvalidRegistration, IOException;
    String showClientInfo(long id) throws NonExistingClient;
    ResponseEntity<String> changeFirstName(Client client, String name) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient, InvalidPasswordCharacters;
    ResponseEntity<String> changeLastName(Client client, String lastName) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient, InvalidPasswordCharacters;
    ResponseEntity<String> changePassword(Client client, String password) throws NonExistingClient, InvalidPhoneNumberCharacters, InvalidEmailCharacters, InvalidPasswordCharacters;
    ResponseEntity<String> changeEmail(Client client, String email) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient, InvalidPasswordCharacters;
    ResponseEntity<String> changePhoneNumber(Client client, String phoneNumber) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient, InvalidPasswordCharacters;
    List<Client> listAllClients();
    ResponseEntity<String> deleteClientInfo(long id) throws NonExistingClient;
}
