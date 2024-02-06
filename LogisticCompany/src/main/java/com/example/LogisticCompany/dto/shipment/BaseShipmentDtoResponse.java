package com.example.LogisticCompany.dto.shipment;

import com.example.LogisticCompany.model.shipment.DeliveryType;
import com.example.LogisticCompany.model.shipment.ShipmentStatus;
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
public class BaseShipmentDtoResponse {
    private int id;

    private String deliveryAddress;

    private BigDecimal weight;

    private LocalDate createdAt;

    private BigDecimal deliveryFee;

    private BigDecimal shipmentCost;

    private ShipmentStatus shipmentStatus;

    private DeliveryType deliveryType;
}
