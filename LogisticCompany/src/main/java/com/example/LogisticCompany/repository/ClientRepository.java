package com.example.LogisticCompany.repository;

import com.example.LogisticCompany.dto.client.ClientDtoResponse;
import com.example.LogisticCompany.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<ClientDtoResponse> findAllClients();
    Optional<ClientDtoResponse> findClientById(int id);
}
