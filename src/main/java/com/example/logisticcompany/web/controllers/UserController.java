package com.example.logisticcompany.web.controllers;

import com.example.logisticcompany.exceptions.InvalidPassword;
import com.example.logisticcompany.exceptions.InvalidRegistration;
import com.example.logisticcompany.exceptions.NonExistingUser;
import com.example.logisticcompany.models.dto.UserDto;
import com.example.logisticcompany.models.entity.User;
import com.example.logisticcompany.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registration(Model model) {


        return "registration";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("user") User user) {
        try {
            userService.register();
        } catch (IOException | InvalidRegistration e) {
            throw new RuntimeException(e);
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestBody UserDto user) {

        try {
            userService.login();
        } catch (IOException | NonExistingUser e) {
            throw new RuntimeException(e);
        } catch (InvalidPassword e) {
            throw new RuntimeException(e);
        }

        return "/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {



        return "redirect:/login?logout";
    }
}
