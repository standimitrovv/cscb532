package com.example.LogisticCompany.controller;

import com.example.LogisticCompany.dto.user.LoginUserDto;
import com.example.LogisticCompany.dto.user.RegisterUserDto;
import com.example.LogisticCompany.dto.user.UserLoginDtoResponse;
import com.example.LogisticCompany.service.implementation.UserServiceImpl;
import jakarta.validation.Valid;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserLoginDtoResponse login(@RequestBody @Valid LoginUserDto userDto){
        return this.userService.login(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterUserDto userDto){
        try {
            this.userService.register(userDto);
            return ResponseEntity.ok().build();
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }
}
