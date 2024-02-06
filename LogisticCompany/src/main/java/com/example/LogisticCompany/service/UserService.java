package com.example.LogisticCompany.service;

import com.example.LogisticCompany.dto.user.LoginUserDto;
import com.example.LogisticCompany.dto.user.RegisterUserDto;
import com.example.LogisticCompany.dto.user.UserDtoResponse;
import com.example.LogisticCompany.dto.user.UserLoginDtoResponse;
import com.example.LogisticCompany.model.user.UserType;
import org.apache.tomcat.websocket.AuthenticationException;

public interface UserService {
    UserLoginDtoResponse login(LoginUserDto userDto);

    void register(RegisterUserDto userDto) throws AuthenticationException;

    UserDtoResponse setUserRole(int userId, UserType userType);
}
