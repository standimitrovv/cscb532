package com.example.logisticcompany.service;

public interface UserAuthenticationService {
    boolean register();
    boolean login();

    // log in and register
    boolean checkIfUsernameExists(String username);

    // log in
    boolean checkIfPasswordIsCorrectForUsername(String password, String username);

    // TODO checkUserRole()
    //UserType checkUserRole(UserAuthentication user);
}
