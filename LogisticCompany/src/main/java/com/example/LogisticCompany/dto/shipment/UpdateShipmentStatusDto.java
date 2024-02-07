package com.example.LogisticCompany.dto.shipment;

import com.example.LogisticCompany.model.shipment.ShipmentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateShipmentStatusDto {
    @NotNull(message = "The 'shipmentStatus' field cannot be null!")
    @Enumerated(EnumType.STRING)
    private ShipmentStatus shipmentStatus;

    @NotNull
    @Min(value = 1, message = "The 'employeeId' field must be a positive integer")
    private int employeeId;
}
