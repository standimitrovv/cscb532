package com.example.LogisticCompany.service;

import com.example.LogisticCompany.dto.user.LoginUserDto;
import com.example.LogisticCompany.dto.user.RegisterUserDto;
import com.example.LogisticCompany.dto.user.UserDtoResponse;
import com.example.LogisticCompany.model.user.UserType;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.websocket.AuthenticationException;

public interface UserService {
    UserDtoResponse login(LoginUserDto userDto, HttpServletResponse response) throws AuthenticationException;

    UserDtoResponse register(RegisterUserDto userDto) throws AuthenticationException;

    void logout(HttpServletResponse response);

    UserDtoResponse setUserRole(int userId, UserType userType);
}
