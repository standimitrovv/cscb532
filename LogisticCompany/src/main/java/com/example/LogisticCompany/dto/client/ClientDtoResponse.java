package com.example.LogisticCompany.dto.client;

import com.example.LogisticCompany.dto.logisticCompany.LogisticCompanyDtoResponse;
import com.example.LogisticCompany.dto.person.PersonDtoResponse;
import com.example.LogisticCompany.dto.shipment.ShipmentDtoResponse;
import com.example.LogisticCompany.dto.user.UserDtoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDtoResponse extends PersonDtoResponse {

    private Set<ShipmentDtoResponse> sentShipments;

    private Set<ShipmentDtoResponse> receivedShipments;

    private Set<LogisticCompanyDtoResponse> companies;
}
