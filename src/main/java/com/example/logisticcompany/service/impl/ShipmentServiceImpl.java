package com.example.logisticcompany.service.impl;

import com.example.logisticcompany.exceptions.*;
import com.example.logisticcompany.models.dto.ShipmentDto;
import com.example.logisticcompany.models.entity.*;
import com.example.logisticcompany.repository.ShipmentRepository;
import com.example.logisticcompany.service.ShipmentService;
import com.example.logisticcompany.util.enums.DeliveryType;
import com.example.logisticcompany.util.enums.UserType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;

    public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public String showShipmentInfo(long id) throws NonExistingShipment {
        if (searchShipmentInRepository(id) != null) {
            Shipment shipment = searchShipmentInRepository(id);
            StringBuilder info = new StringBuilder();

            info.append("Sender: ").append(shipment.getSender().toString());
            info.append(System.lineSeparator());
            info.append("Receiver: ").append(shipment.getReceiver().toString());
            info.append(System.lineSeparator());
            info.append("Delivery Address: ").append(shipment.getDeliveryAddress());
            info.append(System.lineSeparator());
            info.append("Weight: ").append(shipment.getWeight());
            info.append(System.lineSeparator());
            info.append("Shipment's status: ");

            if (shipment.isDelivered()) {
                info.append("delivered");
            } else {
                info.append("sent");
            }
            info.append(System.lineSeparator());

            info.append("Initial sender office: ").append(shipment.getOffice().toString());
            info.append(System.lineSeparator());
            info.append("Date of creation: ").append(shipment.getCreatedAt());
            info.append(System.lineSeparator());
            info.append("Employee to process the shipment: ").append(shipment.getEmployee().toString());
            info.append(System.lineSeparator());
            info.append("Delivery type: ").append(shipment.getDeliveryType().toString());
            info.append(System.lineSeparator());
            info.append("Delivery fee: ").append(shipment.getDeliveryFee());
            info.append(System.lineSeparator());
            info.append("Total price: ").append(this.calculateShipmentPrice(shipment.getId()));
            info.append(System.lineSeparator());

            return info.toString();
        }

        throw new NonExistingShipment("Unable to find a shipment's information with this ID.");
    }

    @Override
    public boolean changeDeliveryType(long id) throws NonExistingShipment {
        if (searchShipmentInRepository(id) != null) {
            Shipment shipment = searchShipmentInRepository(id);

            if (shipment.getDeliveryType().equals(DeliveryType.ADDRESS)) {
                shipment.setDeliveryType(DeliveryType.OFFICE);
            } else if (shipment.getDeliveryType().equals(DeliveryType.OFFICE)) {
                shipment.setDeliveryType(DeliveryType.ADDRESS);
            }

            shipmentRepository.saveAndFlush(shipment);
            return true;
        }

        throw new NonExistingShipment("Unable to change shipment's delivery type with this ID.");
    }

    @Override
    public ResponseEntity<String> changeDeliveryAddressForOffice(Shipment shipment, Office office) throws InvalidType, NonExistingShipment {
        if (searchShipmentInRepository(shipment.getId()) == null) {
            throw new NonExistingShipment("Unable to find the shipment to set the delivery address to!");
        }

        if (shipment.getDeliveryType().equals(DeliveryType.OFFICE)) {
            shipment.setDeliveryAddress(office.getAddress());
            return ResponseEntity.ok("Successfully changed delivery address");
        }

        throw new InvalidType("Unable to set delivery address to be of an office, because the delivery type is ADDRESS!");
    }

    @Override
    public ResponseEntity<String> changeDeliveryAddressForOtherAddress(Shipment shipment, String address) throws InvalidType, NonExistingShipment, InvalidNegativeOrZeroValue {
        if (searchShipmentInRepository(shipment.getId()) == null) {
            throw new NonExistingShipment("Unable to find a shipment with this ID to change his/her delivery address.");
        }

        // create a shipment dto and validate the address
        ShipmentDto shipmentDto = new ShipmentDto(shipment.getSender(), shipment.getReceiver(), address, shipment.getWeight(), shipment.getOffice(), shipment.getPrice(), shipment.getDeliveryType(), shipment.getEmployee());

        // if the address is valid, the current entity shipment will have a new changed address
        shipment.setDeliveryAddress(shipmentDto.getDeliveryAddress());

        // flush to DB
        shipmentRepository.saveAndFlush(shipment);

        return ResponseEntity.ok("Successfully changed delivery address");
    }

    @Override
    public ResponseEntity<String> changeWeight(Shipment shipment, BigDecimal weight) throws InvalidType, InvalidNegativeOrZeroValue, NonExistingShipment {
        if (searchShipmentInRepository(shipment.getId()) == null) {
            throw new NonExistingShipment("Unable to find a shipment with this ID to change his/her weight.");
        }

        // create a shipment dto and validate the weight
        ShipmentDto shipmentDto = new ShipmentDto(shipment.getSender(), shipment.getReceiver(), shipment.getDeliveryAddress(), weight, shipment.getOffice(), shipment.getPrice(), shipment.getDeliveryType(), shipment.getEmployee());

        // if the weight is valid, the current entity shipment will have a new changed weight
        shipment.setWeight(shipmentDto.getWeight());

        // flush to DB
        shipmentRepository.saveAndFlush(shipment);

        return ResponseEntity.ok("Successfully changed weight");
    }

    @Override
    public List<Shipment> getAllRegisteredPackages() {
        return shipmentRepository.findAll();
    }

    @Override
    public List<Shipment> getAllSentAndNotReceivedPackages() {
        return shipmentRepository.findAllByDeliveredIsFalse();
    }

    @Override
    public List<Shipment> getAllRegisteredPackagesByEmployee(long employeeId){
        return shipmentRepository.findAllByEmployeeId(employeeId);
    }

    @Override
    public BigDecimal calculateShipmentPrice(long id) throws NonExistingShipment {
        if (searchShipmentInRepository(id) != null) {
            Shipment shipment = searchShipmentInRepository(id);

            BigDecimal wholePrice = shipment.getPrice().add(shipment.getDeliveryFee());

            //FIXME: not needed?
//            if (shipment.getWeight().compareTo(BigDecimal.valueOf(5)) < 0) {
//                wholePrice = wholePrice.add(BigDecimal.valueOf(4.99));
//            } else if (shipment.getWeight().compareTo(BigDecimal.valueOf(10)) < 0) {
//                wholePrice = wholePrice.add(BigDecimal.valueOf(7.99));
//            } else {
//                wholePrice = wholePrice.add(BigDecimal.valueOf(10.99));
//            }

            return wholePrice;
        }

        throw new NonExistingShipment("Unable to find a shipment with this ID to calculate its shipment price.");
    }

    @Override
    public List<Shipment> getShipmentsBySender(Client client) {
        return shipmentRepository.findAllBySender(client);
    }

    @Override
    public List<Shipment> getNotDeliveredShipmentsByReceiver(Client client) {
        return shipmentRepository.findAllByReceiverAndDeliveredIsFalse(client);
    }

    @Override
    public List<Shipment> getDeliveredShipmentsByReceiver(Client client) {
        return shipmentRepository.findAllByReceiverAndDeliveredIsTrue(client);
    }

    @Override
    public List<Shipment> getShipments(User user) throws InvalidType {
        if (user.getUserType().equals(UserType.EMPLOYEE)) {
            return getAllRegisteredPackages();
        } else if (user.getUserType().equals(UserType.CLIENT)) {
            List<Shipment> allClientShipments = new ArrayList<>();

            allClientShipments.addAll(shipmentRepository.findAllByReceiver(user));
            allClientShipments.addAll(shipmentRepository.findAllBySender(user));

            return allClientShipments;
        } else {
            throw new InvalidType("Invalid user role");
        }
    }

    @Override
    public ResponseEntity<String> deleteShipment(User user, long id) throws NonExistingShipment, InvalidRights {
        if (shipmentRepository.findById(id).isEmpty()) {
            throw new NonExistingShipment("Shipment is non-existing.");
        }

        Shipment shipment = shipmentRepository.findById(id).get();
        if (user.getUserType().equals(UserType.EMPLOYEE)) {
            shipmentRepository.delete(shipment);

            return ResponseEntity.ok("Successfully deleted shipment info");
        }

        throw new InvalidRights("The user cannot delete shipments");
    }

    private Shipment searchShipmentInRepository(long id) {
        if (shipmentRepository.findById(id).isPresent()) {
            return shipmentRepository.findById(id).get();
        }
        return null;
    }
}
