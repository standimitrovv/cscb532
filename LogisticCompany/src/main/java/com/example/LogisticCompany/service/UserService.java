package com.example.LogisticCompany.service;

import com.example.LogisticCompany.dto.user.LoginUserDto;
import com.example.LogisticCompany.dto.user.RegisterUserDto;
import com.example.LogisticCompany.dto.user.UserLoginDtoResponse;
import org.apache.tomcat.websocket.AuthenticationException;

public interface UserService {
    UserLoginDtoResponse login(LoginUserDto userDto);

    void register(RegisterUserDto userDto) throws AuthenticationException;
}
