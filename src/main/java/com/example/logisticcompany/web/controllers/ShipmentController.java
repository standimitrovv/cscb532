package com.example.logisticcompany.web.controllers;

import com.example.logisticcompany.exceptions.InvalidType;
import com.example.logisticcompany.exceptions.NonExistingShipment;
import com.example.logisticcompany.models.entity.Shipment;
import com.example.logisticcompany.models.entity.User;
import com.example.logisticcompany.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    // show all of the shipment info
    // edit shipment info

    @GetMapping("/list")
    public String shipmentList(Model model, User user) {
        List<Shipment> shipments;
        try {
            shipments = shipmentService.getShipments(user);
        } catch (InvalidType e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("shipments", shipments);
        return "shipment-list";
    }

    @GetMapping("/shipment/info{id}")
    public String shipmentInfo() {

        return "shipment-info";
    }

    @GetMapping("/form")
    public String shipmentForm(Model model) {
        model.addAttribute("shipment", new Shipment());
        return "shipment-form";
    }

    @GetMapping("/form/{id}")
    public String editShipmentForm(@PathVariable Long id, Model model) {
        //TODO
        return "shipment-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteShipment() {
        try {
            shipmentService.deleteShipment();
        } catch (NonExistingShipment e) {
            throw new RuntimeException(e);
        }
        return "redirect:/shipments/list";
    }
}
