package com.example.LogisticCompany.service;

import com.example.LogisticCompany.dto.user.LoginUserDto;
import com.example.LogisticCompany.dto.user.RegisterUserDto;
import com.example.LogisticCompany.dto.user.UserDtoResponse;
import com.example.LogisticCompany.model.user.UserType;

public interface UserService {
    UserDtoResponse login(LoginUserDto userDto);

    UserDtoResponse register(RegisterUserDto userDto);

    UserDtoResponse setUserRole(int userId, UserType userType);
}
