package com.example.logisticcompany.service.impl;

import com.example.logisticcompany.exceptions.*;
import com.example.logisticcompany.models.dto.EmployeeDto;
import com.example.logisticcompany.models.dto.UserDto;
import com.example.logisticcompany.models.entity.User;
import com.example.logisticcompany.repository.UserRepository;
import com.example.logisticcompany.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmployeeServiceImpl employeeService;
    private final ClientServiceImpl clientService;

    public UserServiceImpl(UserRepository userRepository, EmployeeServiceImpl employeeService, ClientServiceImpl clientService) {
        this.userRepository = userRepository;
        this.employeeService = employeeService;
        this.clientService = clientService;
    }

    @Override
    public ResponseEntity<String> register(User user) throws InvalidRegistration {
        // check if there is current user who is logged in and is the admin
        // the admin registers in employees!
        if (this.findLoggedInUser() != null && this.findLoggedInUser().isAdmin()) {
            EmployeeDto employeeDto = (EmployeeDto) user;
            employeeService.registerEmployee(user);
            return ResponseEntity.ok("Successfully registered employee");

        } else if (this.findLoggedInUser() == null) { // check if there is no current logged-in user
            clientService.registerClient(user);
            return ResponseEntity.ok("Successfully registered client");
        }

        // if there is already a logged-in user and he/she is a client
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<String> login(UserDto userDto) throws NonExistingUser, InvalidPassword {
        // check if there is already sb logged in
        if (findLoggedInUser() == null) {
            // check if the current user exists - valid email and password

            if (!checkIfUserEmailExists(userDto.getEmail())) {
                throw new NonExistingUser("The typed email is not of an existing user!");
            }

            if (!checkIfPasswordIsCorrectForEmail(userDto.getPassword(), userDto.getEmail())) {
                throw new InvalidPassword("The password for the typed email is wrong!");
            }

            User user = userRepository.findByEmail(userDto.getEmail());
            user.setLoggedIn(true);
            userRepository.saveAndFlush(user);

            return ResponseEntity.ok("Successfully logged in user!");

        }
        // if there is already a logged-in user
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<String> logout() {
        // check if there is sb logged in
        if (findLoggedInUser() != null) {
            User currentUser = findLoggedInUser();
            currentUser.setLoggedIn(false);

            userRepository.saveAndFlush(currentUser);
            return ResponseEntity.ok("Successfully logged out user");
        }
        // if there is no user logged-in
        return ResponseEntity.notFound().build();
    }

    @Override
    public User findLoggedInUser() {
        return userRepository.findByLoggedInIsTrue();
    }

    @Override
    public boolean checkIfUserEmailExists(String email) {
        if (userRepository.findByEmail(email) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkIfPasswordIsCorrectForEmail(String password, String email) {
        if (userRepository.findByEmailAndPassword(email, password) != null) {
            return true;
        }
        return false;
    }
}
