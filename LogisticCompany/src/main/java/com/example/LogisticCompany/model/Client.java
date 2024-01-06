package com.example.LogisticCompany.model;

import com.example.LogisticCompany.model.shipment.Shipment;
import com.example.LogisticCompany.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToOne(mappedBy = "client")
    private User user;

    @OneToMany(mappedBy = "sender")
    private Set<Shipment> sentShipments;

    @OneToMany(mappedBy = "receiver")
    private Set<Shipment> receivedShipments;

    @JsonIgnore
    @ManyToMany(mappedBy = "clients")
    private Set<LogisticCompany> companies;
}
