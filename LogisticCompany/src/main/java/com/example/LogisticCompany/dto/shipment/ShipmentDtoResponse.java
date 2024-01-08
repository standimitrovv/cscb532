package com.example.LogisticCompany.dto.shipment;

import com.example.LogisticCompany.dto.client.ClientDtoResponse;
import com.example.LogisticCompany.dto.employee.EmployeeDtoResponse;
import com.example.LogisticCompany.dto.office.OfficeDtoResponse;
import com.example.LogisticCompany.model.Client;
import com.example.LogisticCompany.model.Office;
import com.example.LogisticCompany.model.employee.Employee;
import com.example.LogisticCompany.model.shipment.DeliveryType;
import com.example.LogisticCompany.model.shipment.ShipmentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDtoResponse {
    private int id;

    private String deliveryAddress;

    private BigDecimal weight;

    private LocalDate createdAt;

    private BigDecimal deliveryFee;

    private BigDecimal shipmentCost;

    private ShipmentStatus shipmentStatus;

    private DeliveryType deliveryType;

    private OfficeDtoResponse office;

    private ClientDtoResponse sender;

    private ClientDtoResponse receiver;

    private EmployeeDtoResponse employee;
}
