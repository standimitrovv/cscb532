package com.example.LogisticCompany.model;

import com.example.LogisticCompany.model.shipment.Shipment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client extends Person {

    @OneToMany
    private Set<Shipment> receivedPackages;

    @OneToMany
    private Set<Shipment> sentPackages;
}
