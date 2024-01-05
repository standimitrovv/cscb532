package com.example.logisticcompany.service;

import com.example.logisticcompany.models.entity.UserAuthentication;
import com.example.logisticcompany.util.UserType;

public interface UserAuthenticationService {
    // log in and register
    boolean checkIfUsernameExists(String username);

    // log in
    boolean checkIfPasswordIsCorrectForUsername(String password, String username);

    // TODO checkUserRole()
    //UserType checkUserRole(UserAuthentication user);
}
