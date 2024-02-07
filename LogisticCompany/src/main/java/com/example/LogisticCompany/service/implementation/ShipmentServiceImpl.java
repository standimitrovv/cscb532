package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.shipment.ShipmentDto;
import com.example.LogisticCompany.dto.shipment.ShipmentDtoResponse;
import com.example.LogisticCompany.dto.shipment.UpdateShipmentStatusDto;
import com.example.LogisticCompany.model.Client;
import com.example.LogisticCompany.model.Office;
import com.example.LogisticCompany.model.employee.Employee;
import com.example.LogisticCompany.model.shipment.DeliveryType;
import com.example.LogisticCompany.model.shipment.Shipment;
import com.example.LogisticCompany.model.shipment.ShipmentStatus;
import com.example.LogisticCompany.repository.ClientRepository;
import com.example.LogisticCompany.repository.EmployeeRepository;
import com.example.LogisticCompany.repository.OfficeRepository;
import com.example.LogisticCompany.repository.ShipmentRepository;
import com.example.LogisticCompany.service.ShipmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final OfficeRepository officeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ShipmentServiceImpl(ShipmentRepository shipmentRepository, ClientRepository clientRepository, EmployeeRepository employeeRepository, OfficeRepository officeRepository) {
        this.shipmentRepository = shipmentRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.officeRepository = officeRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<ShipmentDtoResponse> getAllShipments(int employeeId, ShipmentStatus shipmentStatus, int clientId) {
        List<Shipment> shipments = this.shipmentRepository.findAll();

        return shipments
                .stream()
                .map(sh -> modelMapper.map(sh, ShipmentDtoResponse.class))
                .collect(Collectors.toList());
    }

    public ShipmentDtoResponse getShipment(int shipmentId) {
        Shipment shipment = this.shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return modelMapper.map(shipment, ShipmentDtoResponse.class);
    }

    public ShipmentDtoResponse createNewShipment(ShipmentDto shipmentDto) {
        int senderId = shipmentDto.getSenderId();
        int receiverId = shipmentDto.getReceiverId();
        int officeId = shipmentDto.getOfficeId();

        if(senderId == receiverId){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The sender cannot be the receiver!");
        }

        if(shipmentDto.getDeliveryType() == DeliveryType.OFFICE && (officeId <= 0)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The 'officeId' field is not provided!");
        }

        Client sender = this.clientRepository.findById(senderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender with id: "+ senderId + " was not found"));

        Client receiver = this.clientRepository.findById(receiverId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receiver with id: "+ receiverId + " was not found"));

        Employee employee = this.employeeRepository.findById(shipmentDto.getEmployeeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with id: "+ shipmentDto.getEmployeeId() + " was not found"));

        Shipment shipment = new Shipment();

        Office office;

        if(officeId > 0){
            office = this.officeRepository.findById(officeId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Office with id: "+ officeId + " was not found"));

            shipment.setOffice(office);
        }

        shipment.setSender(sender);
        shipment.setReceiver(receiver);
        shipment.setCreatedByEmployee(employee);
        shipment.setLastUpdatedByEmployee(employee);
        shipment.setDeliveryAddress(shipmentDto.getDeliveryAddress());
        shipment.setWeight(shipmentDto.getWeight());
        shipment.setCreatedAt(LocalDate.now());
        shipment.setDeliveryFee(shipmentDto.getDeliveryFee());
        shipment.setShipmentCost(shipmentDto.getShipmentCost());
        shipment.setShipmentStatus(ShipmentStatus.IN_PROCESS);
        shipment.setDeliveryType(shipmentDto.getDeliveryType());

        this.shipmentRepository.save(shipment);

        return modelMapper.map(shipment, ShipmentDtoResponse.class);
    }

    public ShipmentDtoResponse updateShipmentStatus(int shipmentId, UpdateShipmentStatusDto shipmentDto) {
        Shipment shipment = this.shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        int employeeId = shipmentDto.getEmployeeId();

        Employee employee = this.employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with id: " + employeeId + " was not found"));

        shipment.setShipmentStatus(shipmentDto.getShipmentStatus());
        shipment.setLastUpdatedByEmployee(employee);

        Shipment updatedShipment = shipmentRepository.save(shipment);

        return modelMapper.map(updatedShipment, ShipmentDtoResponse.class);
    }

    public void deleteShipment(int shipmentId) {
        Shipment shipment = this.shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        this.shipmentRepository.delete(shipment);
    }
}
