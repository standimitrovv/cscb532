package com.example.logisticcompany.service;

import com.example.logisticcompany.exceptions.InvalidPassword;
import com.example.logisticcompany.exceptions.InvalidRegistration;
import com.example.logisticcompany.exceptions.NonExistingUser;
import com.example.logisticcompany.models.dto.UserDto;
import com.example.logisticcompany.models.entity.User;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface UserService {
    boolean register() throws IOException, InvalidRegistration;
    ResponseEntity<String> login(UserDto userDto) throws NonExistingUser, InvalidPassword;
    ResponseEntity<String> logout();
    User findLoggedInUser();

    // log in -> if exists - log in, if not - say to register
    // register -> if exists - say it's already created and can log in, if not - register
    boolean checkIfUserEmailExists(String username);

    // log in -> if correct - log in, if not - wrong password
    boolean checkIfPasswordIsCorrectForEmail(String password, String username);
}
