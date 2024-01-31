package com.example.LogisticCompany.repository;

import com.example.LogisticCompany.dto.user.RegisterUserDto;
import com.example.LogisticCompany.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<RegisterUserDto> findUserByUsername(String name);
    Optional<User> findByUsername(String name);
}
