package com.example.LogisticCompany.dto.client;

import com.example.LogisticCompany.dto.logisticCompany.BaseLogisticCompanyDtoResponse;
import com.example.LogisticCompany.dto.person.PersonDtoResponse;
import com.example.LogisticCompany.dto.shipment.BaseShipmentDtoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDtoResponse extends BaseClientDtoResponse {

    private Set<BaseShipmentDtoResponse> sentShipments;

    private Set<BaseShipmentDtoResponse> receivedShipments;

    private Set<BaseLogisticCompanyDtoResponse> companies;
}
