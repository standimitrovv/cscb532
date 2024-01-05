package com.example.logisticcompany.web.controllers;

import com.example.logisticcompany.exceptions.InvalidRegistration;
import com.example.logisticcompany.exceptions.NonExistingOffice;
import com.example.logisticcompany.models.entity.Office;
import com.example.logisticcompany.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/offices")
public class OfficeController {

    @Autowired
    private OfficeService officeService;

    // show all of the office info
    // edit office info

    @GetMapping("/list")
    public String officeList(Model model) {
        List<Office> offices = officeService.showAllOffices();
        model.addAttribute("offices", offices);
        return "office-list";
    }

    @GetMapping("/office_info/{id}")
    public String officeInfo(Model model) {
        String officeAddress = officeService.showAddress();
        String officePhoneNumber = officeService.showPhoneNumber();


        return "office-info";
    }

    @GetMapping("/form")
    public String officeForm(Model model) {
        try {
            model.addAttribute("office", officeService.registerOffice());
        } catch (IOException | InvalidRegistration e) {
            throw new RuntimeException(e);
        }
        return "office-form";
    }


    @PostMapping("/register")
    public String registerOffice(@ModelAttribute Office office) {
        try {
            officeService.registerOffice();
        } catch (IOException | InvalidRegistration e) {
            throw new RuntimeException(e);
        }
        return "redirect:/offices/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteOffice(Long id) {
        try {
            officeService.deleteOfficeInfo(id);
        } catch (NonExistingOffice e) {
            throw new RuntimeException(e);
        }
        return "redirect:/offices/list";
    }
}