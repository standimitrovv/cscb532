package com.example.LogisticCompany.controller;

import com.example.LogisticCompany.dto.user.LoginUserDto;
import com.example.LogisticCompany.dto.user.RegisterUserDto;
import com.example.LogisticCompany.dto.user.UserDtoResponse;
import com.example.LogisticCompany.model.user.UserType;
import com.example.LogisticCompany.service.implementation.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserDtoResponse login(@RequestBody @Valid LoginUserDto userDto){
        return this.userService.login(userDto);
    }

    @PostMapping
    public UserDtoResponse register(@RequestBody @Valid RegisterUserDto userDto){
        return this.userService.register(userDto);
    }

    @PatchMapping("/{userId}")
    public UserDtoResponse setUserType(
            @PathVariable int userId,
            @RequestParam(name = "userType") UserType userType
    ){
        return this.userService.setUserRole(userId, userType);
    }
}
