package com.example.logisticcompany.service.impl;

import com.example.logisticcompany.repository.UserAuthenticationRepository;
import com.example.logisticcompany.service.UserAuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private final UserAuthenticationRepository userAuthenticationRepository;

    public UserAuthenticationServiceImpl(UserAuthenticationRepository userAuthenticationRepository) {
        this.userAuthenticationRepository = userAuthenticationRepository;
    }

    //TODO

    @Override
    public boolean register() {
        return false;
    }

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public boolean checkIfUsernameExists(String username) {
        return false;
    }

    @Override
    public boolean checkIfPasswordIsCorrectForUsername(String password, String username) {
        return false;
    }
}
