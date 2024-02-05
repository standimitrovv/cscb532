package com.example.LogisticCompany.controller;

import com.example.LogisticCompany.dto.user.LoginUserDto;
import com.example.LogisticCompany.dto.user.RegisterUserDto;
import com.example.LogisticCompany.dto.user.UserDtoResponse;
import com.example.LogisticCompany.model.user.UserType;
import com.example.LogisticCompany.service.implementation.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/session")
    public UserDtoResponse login(@RequestBody @Valid LoginUserDto userDto, HttpServletResponse response){
        try {
            return this.userService.login(userDto, response);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public void register(@RequestBody @Valid RegisterUserDto userDto){
        try {
            this.userService.register(userDto);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/{userId}")
    public UserDtoResponse setUserType(
            @PathVariable int userId,
            @RequestParam(name = "userType") UserType userType
    ){
        return this.userService.setUserRole(userId, userType);
    }
}
