package com.example.LogisticCompany.controller;

import com.example.LogisticCompany.dto.user.LoginUserDto;
import com.example.LogisticCompany.dto.user.RegisterUserDto;
import com.example.LogisticCompany.dto.user.UserDtoResponse;
import com.example.LogisticCompany.dto.user.UserLoginDtoResponse;
import com.example.LogisticCompany.model.user.UserType;
import com.example.LogisticCompany.service.implementation.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/{userId}")
    public UserDtoResponse setUserType(
            @PathVariable int userId,
            @RequestParam(name = "userType") UserType userType
    ){
        return this.userService.setUserRole(userId, userType);
    }
}
