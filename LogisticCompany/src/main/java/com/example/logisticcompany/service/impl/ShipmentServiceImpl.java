package com.example.logisticcompany.service.impl;

import com.example.logisticcompany.exceptions.InvalidNegativeOrZeroValue;
import com.example.logisticcompany.exceptions.InvalidType;
import com.example.logisticcompany.exceptions.NonExistingCompany;
import com.example.logisticcompany.exceptions.NonExistingShipment;
import com.example.logisticcompany.models.dto.LogisticCompanyDto;
import com.example.logisticcompany.models.dto.ShipmentDto;
import com.example.logisticcompany.models.entity.*;
import com.example.logisticcompany.repository.ShipmentRepository;
import com.example.logisticcompany.service.ShipmentService;
import com.example.logisticcompany.util.enums.DeliveryType;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;

    public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public JsonObject parseJsonObjectFromFile() throws IOException {
        // TODO: file name
        return JsonParser.parseReader(new FileReader("")).getAsJsonObject();
    }

    @Override
    public boolean registerShipment(Shipment shipment) {
        return false;
    }

    @Override
    public Client showSender(long id) throws NonExistingShipment {
        if (searchShipmentInRepository(id) != null) {
            Shipment shipment = searchShipmentInRepository(id);
            return shipment.getSender();
        }

        throw new NonExistingShipment("Unable to find a shipment's sender with this ID.");
    }

    @Override
    public Client showReceiver(long id) throws NonExistingShipment {
        if (searchShipmentInRepository(id) != null) {
            Shipment shipment = searchShipmentInRepository(id);
            return shipment.getReceiver();
        }

        throw new NonExistingShipment("Unable to find a shipment's receiver with this ID.");
    }

    @Override
    public String showDeliveryAddress(long id) throws NonExistingShipment {
        if (searchShipmentInRepository(id) != null) {
            Shipment shipment = searchShipmentInRepository(id);
            return shipment.getDeliveryAddress();
        }

        throw new NonExistingShipment("Unable to find a shipment's delivery address with this ID.");
    }

    @Override
    public BigDecimal showWeight(long id) throws NonExistingShipment {
        if (searchShipmentInRepository(id) != null) {
            Shipment shipment = searchShipmentInRepository(id);
            return shipment.getWeight();
        }

        throw new NonExistingShipment("Unable to find a shipment's weight with this ID.");
    }

    @Override
    public boolean checkIfSent(long id) throws NonExistingShipment {
        if (searchShipmentInRepository(id) != null) {
            Shipment shipment = searchShipmentInRepository(id);
            return shipment.isSent();
        }

        throw new NonExistingShipment("Unable to find a shipment with this ID.");
    }

    @Override
    public boolean checkIfDelivered(long id) throws NonExistingShipment {
        if (searchShipmentInRepository(id) != null) {
            Shipment shipment = searchShipmentInRepository(id);
            return shipment.isDelivered();
        }

        throw new NonExistingShipment("Unable to find a shipment with this ID.");
    }

    @Override
    public Office showOffice(long id) throws NonExistingShipment {
        if (searchShipmentInRepository(id) != null) {
            Shipment shipment = searchShipmentInRepository(id);
            return shipment.getOffice();
        }

        throw new NonExistingShipment("Unable to find a shipment's office from which it was sent with this ID.");
    }

    @Override
    public LocalDate showDateOfCreation(long id) throws NonExistingShipment {
        if (searchShipmentInRepository(id) != null) {
            Shipment shipment = searchShipmentInRepository(id);
            return shipment.getCreatedAt();
        }

        throw new NonExistingShipment("Unable to find a shipment's creation date with this ID.");
    }

    @Override
    public BigDecimal showDeliveryFee(long id) throws NonExistingShipment {
        if (searchShipmentInRepository(id) != null) {
            Shipment shipment = searchShipmentInRepository(id);
            return shipment.getDeliveryFee();
        }

        throw new NonExistingShipment("Unable to find a shipment's delivery fee with this ID.");
    }

    @Override
    public BigDecimal showPrice(long id) throws NonExistingShipment {
        if (searchShipmentInRepository(id) != null) {
            Shipment shipment = searchShipmentInRepository(id);
            return shipment.getPrice();
        }

        throw new NonExistingShipment("Unable to find a shipment's price with this ID.");
    }

    @Override
    public BigDecimal showPriceWithDeliveryFee(long id) throws NonExistingShipment {
        if (searchShipmentInRepository(id) != null) {
            Shipment shipment = searchShipmentInRepository(id);
            return shipment.getPriceWithDeliveryFee();
        }

        throw new NonExistingShipment("Unable to find a shipment's price with delivery fee with this ID.");
    }

    @Override
    public DeliveryType showDeliveryType(long id) throws NonExistingShipment {
        if (searchShipmentInRepository(id) != null) {
            Shipment shipment = searchShipmentInRepository(id);
            return shipment.getDeliveryType();
        }

        throw new NonExistingShipment("Unable to find a shipment's delivery type with this ID.");
    }

    @Override
    public Employee showEmployee(long id) throws NonExistingShipment {
        if (searchShipmentInRepository(id) != null) {
            Shipment shipment = searchShipmentInRepository(id);
            return shipment.getEmployee();
        }

        throw new NonExistingShipment("Unable to find a shipment's employee sender with this ID.");
    }

    @Override
    public boolean changeDeliveryType(long id) {
        return false;
    }

    @Override
    public boolean changeEmployee(long id) {
        return false;
    }

    @Override
    public boolean changeDeliveryAddress(long id) {
        return false;
    }

    @Override
    public boolean changeWeight(long id) throws InvalidType, InvalidNegativeOrZeroValue, NonExistingShipment {
        if (searchShipmentInRepository(id) != null) {
            // get the current shipment entity (if existing) by id
            Shipment shipment = searchShipmentInRepository(id);
            String jsonWeight;

            try {
                // read the file and get it (parse it) as a json object
                JsonObject jsonObject = parseJsonObjectFromFile();

                // check if the json object has weight as a key and get the value
                if (jsonObject.has("weight")) {
                    jsonWeight= jsonObject.get("weight").getAsString();

                    // create a shipment dto and validate the weight
                    ShipmentDto shipmentDto = new ShipmentDto(shipment.getSender(), shipment.getReceiver(), shipment.getDeliveryAddress(), BigDecimal.valueOf(Long.parseLong(jsonWeight)), shipment.getOffice(), shipment.getPrice(), shipment.getDeliveryType(), shipment.getEmployee());

                    // if the weight is valid, the current entity shipment will have a new changed weight
                    shipment.setWeight(shipmentDto.getWeight());

                    // flush to DB
                    shipmentRepository.saveAndFlush(shipment);

                    // TODO: clear info in the json file

                    return true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        throw new NonExistingShipment("Unable to find a shipment with this ID to change its weight.");
    }

    @Override
    public boolean changeOffice(long id) {
        return false;
    }

    @Override
    public boolean changeDeliveryFee(long id) {
        return false;
    }

    @Override
    public boolean changePrice(long id) {
        return false;
    }

    @Override
    public boolean changePriceWithDeliveryFee(long id) {
        return false;
    }

    @Override
    public boolean deleteShipment(long id) {
        return false;
    }

    private Shipment searchShipmentInRepository(long id) {
        if (shipmentRepository.findById(id).isPresent()) {
            return shipmentRepository.findById(id).get();
        }
        return null;
    }
}
