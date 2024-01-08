package com.example.LogisticCompany.controller;

import com.example.LogisticCompany.dto.client.ClientDto;
import com.example.LogisticCompany.dto.client.ClientDtoResponse;
import com.example.LogisticCompany.service.implementation.ClientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientServiceImpl clientService;

    public ClientController(ClientServiceImpl clientService){
        this.clientService = clientService;
    }

    @GetMapping
    public ClientDtoResponse getAllClients() {
        return this.clientService.getAllClients();
    }

    @GetMapping("/{clientId}")
    public ClientDtoResponse getClient(@PathVariable int clientId) {
        return this.clientService.getClient(clientId);
    }

    @PostMapping
    public ClientDtoResponse createNewClient(@RequestBody @Valid ClientDto clientDto) {
        return this.clientService.createNewClient(clientDto);
    }

    @PatchMapping("/{clientId}")
    public ClientDtoResponse updateClient(@PathVariable int clientId, ClientDto clientDto) {
        return this.clientService.updateClient(clientId, clientDto);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable int clientId) {
        this.clientService.deleteClient(clientId);

        return ResponseEntity.ok("Client with id: '" + clientId + "' was successfully deleted!");
    }
}