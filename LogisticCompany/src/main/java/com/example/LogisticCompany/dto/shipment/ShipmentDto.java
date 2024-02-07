package com.example.LogisticCompany.dto.shipment;

import com.example.LogisticCompany.model.shipment.DeliveryType;
import com.example.LogisticCompany.model.shipment.ShipmentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDto {
    @NotBlank(message = "The 'deliveryAddress' field cannot be blank!")
    @Size(min = 5, max = 45, message = "The 'deliveryAddress' field has to contain at least 5 and at most 50 characters!")
    private String deliveryAddress;

    @NotNull(message = "The 'weight' field cannot be null!")
    @Digits(integer = 2, fraction = 1, message = "The 'weight' field can have at most 3 digits before the fraction")
    private BigDecimal weight;

    @NotNull(message = "The 'deliveryFee' field cannot be null!")
    @Digits(integer = 2, fraction = 2, message = "The 'deliveryFee' field can have at most 4 digits before the fraction")
    private BigDecimal deliveryFee;

    @NotNull(message = "The 'shipmentCost' field cannot be null!")
    @Digits(integer = 3, fraction = 2, message = "The 'shipmentCost' field can have at most 5 digits before the fraction")
    private BigDecimal shipmentCost;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus shipmentStatus;

    @NotNull(message = "The 'deliveryType' field cannot be null!")
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @NotNull
    @Positive
    @Min(value = 1, message = "The 'senderId' field must be a positive integer")
    private int senderId;

    @NotNull
    @Positive
    @Min(value = 1, message = "The 'receiverId' field must be a positive integer")
    private int receiverId;

    @NotNull
    @Positive
    @Min(value = 1, message = "The 'employeeId' field must be a positive integer")
    private int employeeId;

    private int officeId;
}
