package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.user.LoginUserDto;
import com.example.LogisticCompany.dto.user.RegisterUserDto;
import com.example.LogisticCompany.dto.user.UserDtoResponse;
import com.example.LogisticCompany.repository.UserRepository;
import com.example.LogisticCompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDtoResponse login(LoginUserDto userDto) {
        return null;
    }

    public UserDtoResponse register(RegisterUserDto userDto) {
        return null;
    }
}
