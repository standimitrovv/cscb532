package com.example.LogisticCompany.repository;

import com.example.LogisticCompany.dto.client.BaseClientDtoResponse;
import com.example.LogisticCompany.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query(value = "SELECT c FROM Client c WHERE c.user.id = :userId")
    Client findClientByUserId(@Param("userId") int userId);
}
