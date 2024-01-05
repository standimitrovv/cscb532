package com.example.logisticcompany.service.impl;

import com.example.logisticcompany.exceptions.InvalidEmailCharacters;
import com.example.logisticcompany.exceptions.InvalidPhoneNumberCharacters;
import com.example.logisticcompany.exceptions.NonExistingClient;
import com.example.logisticcompany.models.dto.ClientDto;
import com.example.logisticcompany.models.entity.Client;
import com.example.logisticcompany.models.entity.Shipment;
import com.example.logisticcompany.repository.ClientRepository;
import com.example.logisticcompany.service.ClientService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public JsonObject parseJsonObjectFromFile() throws IOException {
        // TODO: file name
        return JsonParser.parseReader(new FileReader("")).getAsJsonObject();
    }

    @Override
    public boolean registerClient(Client client) {
        // if this client already exists -> do not register him again
        if (clientRepository.findById(client.getId()).isPresent()) {
            //TODO
            return false;
        }
        return true;
    }

    @Override
    public String showName(long id) throws NonExistingClient {
        if (searchClientInRepository(id) != null) {
            Client client = searchClientInRepository(id);
            return client.getFirstName() + " " + client.getLastName();
        }

        throw new NonExistingClient("Unable to find a client's name with this ID.");
    }

    @Override
    public String showAddress(long id) throws NonExistingClient {
        if (searchClientInRepository(id) != null) {
            Client client = searchClientInRepository(id);
            return client.getAddress();
        }

        throw new NonExistingClient("Unable to find a client's address with this ID.");
    }

    @Override
    public String showEmail(long id) throws NonExistingClient {
        if (searchClientInRepository(id) != null) {
            Client client = searchClientInRepository(id);
            return client.getEmail();
        }

        throw new NonExistingClient("Unable to find a client's email with this ID.");
    }

    @Override
    public String showPhoneNumber(long id) throws NonExistingClient {
        if (searchClientInRepository(id) != null) {
            Client client = searchClientInRepository(id);
            return client.getPhoneNumber();
        }

        throw new NonExistingClient("Unable to find a client's phone number with this ID.");
    }

    @Override
    public Set<Shipment> showReceivedPackages(long id) throws NonExistingClient {
        if (searchClientInRepository(id) != null) {
            Client client = searchClientInRepository(id);
            return client.getReceivedPackages();
        }

        throw new NonExistingClient("Unable to find client's received packages with this ID.");
    }

    @Override
    public Set<Shipment> showSentPackages(long id) throws NonExistingClient {
        if (searchClientInRepository(id) != null) {
            Client client = searchClientInRepository(id);
            return client.getSentPackages();
        }

        throw new NonExistingClient("Unable to find client's sent packages with this ID.");
    }

    @Override
    public Set<Shipment> showToBeReceivedPackages(long id) throws NonExistingClient {
        if (searchClientInRepository(id) != null) {
            Client client = searchClientInRepository(id);
            return client.getToBeReceived();
        }

        throw new NonExistingClient("Unable to find client's expected to be received packages with this ID.");
    }

    @Override
    public boolean changeFirstName(long id) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient {
        if (searchClientInRepository(id) != null) {
            // get the current client entity (if existing) by id
            Client client = searchClientInRepository(id);
            String jsonName;

            try {
                // read the file and get it (parse it) as a json object
                JsonObject jsonObject = parseJsonObjectFromFile();

                // check if the json object has first_name as a key and get the value
                if (jsonObject.has("first_name")) {
                    jsonName = jsonObject.get("first_name").getAsString();

                    // create a client dto and validate the name
                    ClientDto clientDto = new ClientDto(jsonName, client.getLastName(), client.getAddress(), client.getEmail(), client.getPhoneNumber());

                    // if the name is valid, the current entity client will have a new changed name
                    client.setFirstName(clientDto.getFirstName());

                    // flush to DB
                    clientRepository.saveAndFlush(client);

                    // TODO: clear info in the json file

                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new NonExistingClient("Unable to find a client with this ID to change his/her first name.");
    }

    @Override
    public boolean changeLastName(long id) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient {
        if (searchClientInRepository(id) != null) {
            // get the current client entity (if existing) by id
            Client client = searchClientInRepository(id);
            String jsonName;

            try {
                // read the file and get it (parse it) as a json object
                JsonObject jsonObject = parseJsonObjectFromFile();

                // check if the json object has last_name as a key and get the value
                if (jsonObject.has("last_name")) {
                    jsonName = jsonObject.get("last_name").getAsString();

                    // create a client dto and validate the name
                    ClientDto clientDto = new ClientDto(client.getFirstName(), jsonName, client.getAddress(), client.getEmail(), client.getPhoneNumber());

                    // if the name is valid, the current entity client will have a new changed name
                    client.setLastName(clientDto.getLastName());

                    // flush to DB
                    clientRepository.saveAndFlush(client);

                    // TODO: clear info in the json file

                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new NonExistingClient("Unable to find a client with this ID to change his/her last name.");
    }

    @Override
    public boolean changeAddress(long id) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient {
        if (searchClientInRepository(id) != null) {
            // get the current client entity (if existing) by id
            Client client = searchClientInRepository(id);
            String jsonAddress;

            try {
                // read the file and get it (parse it) as a json object
                JsonObject jsonObject = parseJsonObjectFromFile();

                // check if the json object has address as a key and get the value
                if (jsonObject.has("first_name")) {
                    jsonAddress = jsonObject.get("first_name").getAsString();

                    // create a client dto and validate the address
                    ClientDto clientDto = new ClientDto(client.getFirstName(), client.getLastName(), jsonAddress, client.getEmail(), client.getPhoneNumber());

                    // if the address is valid, the current entity client will have a new changed address
                    client.setAddress(clientDto.getAddress());

                    // flush to DB
                    clientRepository.saveAndFlush(client);

                    // TODO: clear info in the json file

                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new NonExistingClient("Unable to find a client with this ID to change his/her address.");
    }

    @Override
    public boolean changeEmail(long id) throws InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient {
        if (searchClientInRepository(id) != null) {
            // get the current client entity (if existing) by id
            Client client = searchClientInRepository(id);
            String jsonEmail;

            try {
                // read the file and get it (parse it) as a json object
                JsonObject jsonObject = parseJsonObjectFromFile();

                // check if the json object has email as a key and get the value
                if (jsonObject.has("email")) {
                    jsonEmail = jsonObject.get("email").getAsString();

                    // create a client dto and validate the email
                    ClientDto clientDto = new ClientDto(client.getFirstName(), client.getLastName(), client.getAddress(), jsonEmail, client.getPhoneNumber());

                    // if the email is valid, the current entity client will have a new changed email
                    client.setEmail(clientDto.getEmail());

                    // flush to DB
                    clientRepository.saveAndFlush(client);

                    // TODO: clear info in the json file

                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new NonExistingClient("Unable to find a client with this ID to change his/her email.");
    }

    @Override
    public boolean changePhoneNumber(long id) throws IOException, InvalidPhoneNumberCharacters, InvalidEmailCharacters, NonExistingClient {
        if (searchClientInRepository(id) != null) {
            // get the current client entity (if existing) by id
            Client client = searchClientInRepository(id);
            String jsonPhoneNumber;

            try {
                // read the file and get it (parse it) as a json object
                JsonObject jsonObject = parseJsonObjectFromFile();

                // check if the json object has phone number as a key and get the value
                if (jsonObject.has("phone_number")) {
                    jsonPhoneNumber = jsonObject.get("phone_number").getAsString();

                    // create a client dto and validate the phone number
                    ClientDto clientDto = new ClientDto(client.getFirstName(), client.getLastName(), client.getAddress(), client.getEmail(), jsonPhoneNumber);

                    // if the phone number is valid, the current entity client will have a new changed phone number
                    client.setPhoneNumber(clientDto.getPhoneNumber());

                    // flush to DB
                    clientRepository.saveAndFlush(client);

                    // TODO: clear info in the json file

                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new NonExistingClient("Unable to find a client with this ID to change his/her phone number.");
    }

    @Override
    public boolean addNewReceivedPackage(long clientId, long packageId) {
        return false;
    }

    @Override
    public boolean addNewSentPackage(long id) {
        return false;
    }

    @Override
    public boolean addNewToBeReceivedPackage(long id) {
        return false;
    }

    @Override
    public boolean deleteClientInfo(long id) {
        return false;
    }

    private Client searchClientInRepository(long id) {
        if (clientRepository.findById(id).isPresent()) {
            return clientRepository.findById(id).get();
        }
        return null;
    }
}
