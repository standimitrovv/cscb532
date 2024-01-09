package com.example.LogisticCompany.service;

import com.example.LogisticCompany.dto.user.LoginUserDto;
import com.example.LogisticCompany.dto.user.RegisterUserDto;
import com.example.LogisticCompany.dto.user.UserDtoResponse;

public interface UserService {
    UserDtoResponse login(LoginUserDto userDto);

    UserDtoResponse register(RegisterUserDto userDto);
}
