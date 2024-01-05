package com.example.logisticcompany.repository;

import com.example.logisticcompany.models.entity.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthenticationRepository extends JpaRepository<UserAuthentication, Long> {

    UserAuthentication findByUserName(String username);
    UserAuthentication findByUserNameAndPassword(String username, String password);
}
