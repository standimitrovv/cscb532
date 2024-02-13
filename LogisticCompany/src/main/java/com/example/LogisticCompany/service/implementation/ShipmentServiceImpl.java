package com.example.LogisticCompany.service.implementation;

import com.example.LogisticCompany.dto.shipment.ShipmentDto;
import com.example.LogisticCompany.dto.shipment.ShipmentDtoResponse;
import com.example.LogisticCompany.dto.shipment.UpdateShipmentStatusDto;
import com.example.LogisticCompany.model.Client;
import com.example.LogisticCompany.model.Office;
import com.example.LogisticCompany.model.employee.Employee;
import com.example.LogisticCompany.model.logisticCompany.LogisticCompany;
import com.example.LogisticCompany.model.shipment.DeliveryType;
import com.example.LogisticCompany.model.shipment.Shipment;
import com.example.LogisticCompany.model.shipment.ShipmentStatus;
import com.example.LogisticCompany.repository.*;
import com.example.LogisticCompany.service.ShipmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final OfficeRepository officeRepository;
    private final LogisticCompanyRepository logisticCompanyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ShipmentServiceImpl(
            ShipmentRepository shipmentRepository,
            ClientRepository clientRepository,
            EmployeeRepository employeeRepository,
            OfficeRepository officeRepository,
            LogisticCompanyRepository logisticCompanyRepository
    ) {
        this.shipmentRepository = shipmentRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.officeRepository = officeRepository;
        this.logisticCompanyRepository = logisticCompanyRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<ShipmentDtoResponse> getAllShipments(int employeeId, ShipmentStatus shipmentStatus, int clientId) {
        Stream<Shipment> shipments = this.shipmentRepository.findAll().stream();

        boolean isAnExistingEmployee = this.employeeRepository.existsById(employeeId);

        if(employeeId > 0 && isAnExistingEmployee){
            return this.convertShipmentListToDtoResponse(
                    shipments.filter(sh -> sh.getRegisteredByEmployee().getId() == employeeId)
            );
        }

        if(employeeId == 0 && shipmentStatus == null && clientId == 0){
            return this.convertShipmentListToDtoResponse(shipments);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
        }
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

        LogisticCompany company = this.logisticCompanyRepository.findById(1)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A logistic company with id: "+ 1 + " was not found"));

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

        Set<Client> logisticCompanyClients = company.getClients();

        boolean isSenderACurrentClient = logisticCompanyClients.stream().anyMatch(c -> c.getId() == senderId);
        if(!isSenderACurrentClient){
            logisticCompanyClients.add(sender);
        }

        boolean isReceiverACurrentClient = logisticCompanyClients.stream().anyMatch(c -> c.getId() == receiverId);
        if(!isReceiverACurrentClient){
            logisticCompanyClients.add(receiver);
        }

        if(!isSenderACurrentClient || !isReceiverACurrentClient){
            logisticCompanyRepository.save(company);
        }

        shipment.setSender(sender);
        shipment.setReceiver(receiver);
        shipment.setRegisteredByEmployee(employee);
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

    private List<ShipmentDtoResponse> convertShipmentListToDtoResponse(Stream<Shipment> shipments){
        return shipments
                .map(sh -> modelMapper.map(sh, ShipmentDtoResponse.class))
                .collect(Collectors.toList());
    }
}
